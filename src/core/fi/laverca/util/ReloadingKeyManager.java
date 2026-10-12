/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2026 Laverca Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fi.laverca.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Client key manager over a keystore file that is reloaded when the file changes.
 * <p>
 * Of the keystore's private keys, the client certificate is the oldest one (earliest notBefore) that has not expired,
 * preferring keys already valid. When it expires, the next one takes over. When all have expired, the one that
 * expired last stays in use. A keystore without private keys sends no client certificate, as before 4.0.0.
 * <p>
 * {@link #refresh()} re-reads the file at most once per check interval, and at once when the key in use expires.
 * {@link #getGeneration()} changes whenever the key in use changes, so connections made with the old key can be dropped.
 */
public class ReloadingKeyManager extends X509ExtendedKeyManager {

    private static final Log log = LogFactory.getLog(ReloadingKeyManager.class);

    /** Default keystore check interval in seconds. */
    public static final int DEFAULT_CHECK_INTERVAL = 30;

    /** A private key entry of the keystore. */
    private static final class Entry {
        final String            alias;
        final PrivateKey        key;
        final X509Certificate[] chain;

        Entry(final String alias, final PrivateKey key, final X509Certificate[] chain) {
            this.alias = alias;
            this.key   = key;
            this.chain = chain;
        }

        long notBefore() {
            return this.chain[0].getNotBefore().getTime();
        }

        long notAfter() {
            return this.chain[0].getNotAfter().getTime();
        }
    }

    private final Path   file;
    private final char[] password;
    private final String type;
    private final long   intervalMillis;

    /** The keystore's private key entries. */
    private List<Entry> entries;
    private byte[]      digest;
    private long        nextCheck;

    /** The key in use (null without private keys), and the one before it for handshakes that chose it just before a change. */
    private volatile Entry selected;
    private volatile Entry previous;
    private volatile long  generation;

    /**
     * Loads the keystore and chooses the client key.
     *
     * @param file keystore file
     * @param password keystore and key password
     * @param type keystore type, e.g. PKCS12 or JKS
     * @param intervalSeconds how often the file is checked for changes, in seconds; 0 to load it only once
     * @throws IOException if the keystore cannot be read
     * @throws GeneralSecurityException if the keystore cannot be loaded
     */
    public ReloadingKeyManager(final String file, final String password, final String type, final int intervalSeconds)
        throws IOException, GeneralSecurityException
    {
        this.file           = Path.of(file);
        this.password       = (password != null) ? password.toCharArray() : new char[0];
        this.type           = type;
        this.intervalMillis = Math.max(intervalSeconds, 0) * 1000L;

        final byte[] content = Files.readAllBytes(this.file);
        this.entries   = this.load(content);
        this.digest    = sha256(content);
        this.selected  = choose(this.entries, System.currentTimeMillis());
        this.nextCheck = System.currentTimeMillis() + this.intervalMillis;
        log.info("Client key " + describe(this.selected) + " from " + this.file);
    }

    /**
     * Re-reads the keystore file if the check interval has passed, and chooses the client key again.
     * Does it also at once when the key in use has expired and another key may take over.
     * A file that cannot be loaded, e.g. one being written, leaves the current keys in use until the next check.
     */
    public synchronized void refresh() {
        final long  now     = System.currentTimeMillis();
        final Entry current = this.selected;
        final boolean expired = current != null && now >= current.notAfter() && hasUnexpired(this.entries, now);
        if (now < this.nextCheck && !expired) {
            return;
        }
        this.nextCheck = now + ((this.intervalMillis > 0) ? this.intervalMillis : Long.MAX_VALUE / 2);

        if (this.intervalMillis > 0) {
            try {
                final byte[] content = Files.readAllBytes(this.file);
                final byte[] newDigest = sha256(content);
                if (!Arrays.equals(newDigest, this.digest)) {
                    this.entries = this.load(content);
                    this.digest  = newDigest;
                    log.info("Reloaded " + this.file + ": " + this.entries.size() + " private key(s)");
                }
            } catch (IOException | GeneralSecurityException e) {
                log.warn("Keeping the current client keys: cannot load " + this.file + ": " + e);
            }
        }

        final Entry chosen = choose(this.entries, now);
        if (!sameEntry(chosen, current)) {
            this.previous   = current;
            this.selected   = chosen;
            this.generation++;
            log.info("Client key changed to " + describe(chosen));
        }
    }

    /**
     * Gets a counter that changes whenever the key in use changes.
     *
     * @return the generation of the key in use
     */
    public long getGeneration() {
        return this.generation;
    }

    /**
     * Gets the alias of the key in use.
     *
     * @return the alias, or null without private keys
     */
    public String getSelectedAlias() {
        final Entry s = this.selected;
        return (s != null) ? s.alias : null;
    }

    /**
     * Chooses the client key: the oldest unexpired one, preferring keys already valid; else the one that expired last.
     *
     * @param entries the private key entries
     * @param now the current time in milliseconds
     * @return the chosen entry, or null if there are none
     */
    private static Entry choose(final List<Entry> entries, final long now) {
        Entry best = null;
        for (final Entry e : entries) {
            if (now >= e.notAfter()) {
                continue;
            }
            if (best == null) {
                best = e;
                continue;
            }
            final boolean valid     = now >= e.notBefore();
            final boolean bestValid = now >= best.notBefore();
            if (valid != bestValid ? valid : e.notBefore() < best.notBefore()) {
                best = e;
            }
        }
        if (best == null) {
            // All expired: keep the one that expired last
            for (final Entry e : entries) {
                if (best == null || e.notAfter() > best.notAfter()) {
                    best = e;
                }
            }
        }
        return best;
    }

    /**
     * Loads the private key entries of a keystore.
     *
     * @param content the keystore file's content
     * @return the entries, empty if the keystore has no private keys
     * @throws IOException if the keystore cannot be parsed
     * @throws GeneralSecurityException if the keystore cannot be loaded
     */
    private List<Entry> load(final byte[] content) throws IOException, GeneralSecurityException {
        final KeyStore ks = KeyStore.getInstance(this.type);
        ks.load(new ByteArrayInputStream(content), this.password);

        final List<Entry> list = new ArrayList<>();
        for (final Enumeration<String> aliases = ks.aliases(); aliases.hasMoreElements();) {
            final String alias = aliases.nextElement();
            if (!ks.isKeyEntry(alias)) {
                continue;
            }
            final Key key = ks.getKey(alias, this.password);
            final Certificate[] chain = ks.getCertificateChain(alias);
            if (!(key instanceof PrivateKey) || chain == null || chain.length == 0) {
                continue;
            }
            final X509Certificate[] x509 = new X509Certificate[chain.length];
            boolean ok = true;
            for (int i = 0; i < chain.length; i++) {
                if (chain[i] instanceof X509Certificate c) {
                    x509[i] = c;
                } else {
                    ok = false;
                }
            }
            if (ok) {
                list.add(new Entry(alias, (PrivateKey) key, x509));
            }
        }
        return Collections.unmodifiableList(list);
    }

    private static boolean hasUnexpired(final List<Entry> entries, final long now) {
        for (final Entry e : entries) {
            if (now < e.notAfter()) {
                return true;
            }
        }
        return false;
    }

    private static boolean sameEntry(final Entry a, final Entry b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.alias.equals(b.alias) && a.chain[0].equals(b.chain[0]);
    }

    private static String describe(final Entry e) {
        if (e == null) {
            return "none (no private keys)";
        }
        final X509Certificate c = e.chain[0];
        return "'" + e.alias + "' (" + c.getSubjectX500Principal().getName() + ", serial " + c.getSerialNumber().toString(16)
               + ", valid " + c.getNotBefore().toInstant() + " - " + c.getNotAfter().toInstant() + ")";
    }

    private static byte[] sha256(final byte[] content) throws GeneralSecurityException {
        return MessageDigest.getInstance("SHA-256").digest(content);
    }

    /**
     * Finds an entry by alias: the key in use, or the one before it.
     *
     * @param alias the alias
     * @return the entry, or null
     */
    private Entry find(final String alias) {
        final Entry s = this.selected;
        if (s != null && s.alias.equals(alias)) {
            return s;
        }
        final Entry p = this.previous;
        if (p != null && p.alias.equals(alias)) {
            return p;
        }
        return null;
    }

    /**
     * Tells whether the key in use fits one of the key types a TLS handshake asks for.
     *
     * @param keyTypes key type names such as RSA, EC or EC_RSA
     * @return the alias of the key in use, or null if it does not fit
     */
    private String aliasFor(final String... keyTypes) {
        final Entry s = this.selected;
        if (s == null || keyTypes == null) {
            return null;
        }
        final String alg = s.key.getAlgorithm();
        for (final String keyType : keyTypes) {
            if (keyType == null) {
                continue;
            }
            final int us = keyType.indexOf('_');
            final String base = (us > 0) ? keyType.substring(0, us) : keyType;
            if (base.equalsIgnoreCase(alg)
                || ("RSASSA-PSS".equalsIgnoreCase(base) && "RSA".equalsIgnoreCase(alg))
                || ("EdDSA".equalsIgnoreCase(base) && alg.startsWith("Ed"))) {
                return s.alias;
            }
        }
        return null;
    }

    @Override
    public String[] getClientAliases(final String keyType, final Principal[] issuers) {
        final String alias = this.aliasFor(keyType);
        return (alias != null) ? new String[] {alias} : null;
    }

    @Override
    public String chooseClientAlias(final String[] keyTypes, final Principal[] issuers, final Socket socket) {
        return this.aliasFor(keyTypes);
    }

    @Override
    public String chooseEngineClientAlias(final String[] keyTypes, final Principal[] issuers, final SSLEngine engine) {
        return this.aliasFor(keyTypes);
    }

    @Override
    public String[] getServerAliases(final String keyType, final Principal[] issuers) {
        return null;
    }

    @Override
    public String chooseServerAlias(final String keyType, final Principal[] issuers, final Socket socket) {
        return null;
    }

    @Override
    public String chooseEngineServerAlias(final String keyType, final Principal[] issuers, final SSLEngine engine) {
        return null;
    }

    @Override
    public X509Certificate[] getCertificateChain(final String alias) {
        final Entry e = this.find(alias);
        return (e != null) ? e.chain.clone() : null;
    }

    @Override
    public PrivateKey getPrivateKey(final String alias) {
        final Entry e = this.find(alias);
        return (e != null) ? e.key : null;
    }
}
