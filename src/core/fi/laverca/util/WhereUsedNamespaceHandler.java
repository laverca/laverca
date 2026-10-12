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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * SAX filter that declares each namespace on the element that uses it, as Axis1's serializer did, instead of every
 * namespace of the JAXB context on the root (JAXB RI declares all of them before the first element).
 * <p>
 * The root element is recorded and replayed to the target at its end: a declaration whose prefix starts a text or
 * attribute value (QName content, e.g. an xsi:type) stays where it was, which is known only after the whole element.
 * Prefixes are the ones JAXB chose from {@link JMarshallerFactory}'s prefix mapper; a declaration already in scope
 * with the same binding is not repeated.
 */
public final class WhereUsedNamespaceHandler implements ContentHandler {

    private static final Pattern VALUE_PREFIX = Pattern.compile("^\\s*([A-Za-z_][\\w.-]*):");

    /** A recorded element */
    private static final class Element {
        final String uri;
        final String localName;
        final String qName;
        final List<String[]> declarations; // prefix, URI as fed
        final Attributes attributes;
        final List<Object> children = new ArrayList<>(); // Element or StringBuilder

        Element(final String uri, final String localName, final String qName, final List<String[]> declarations, final Attributes attributes) {
            this.uri = uri;
            this.localName = localName;
            this.qName = qName;
            this.declarations = declarations;
            this.attributes = new AttributesImpl(attributes);
        }
    }

    private final ContentHandler target;

    private List<String[]> pending = new ArrayList<>();
    private final Deque<Element> open = new ArrayDeque<>();
    private Element root;

    /**
     * Creates the filter.
     *
     * @param target the handler to replay the document to
     */
    public WhereUsedNamespaceHandler(final ContentHandler target) {
        this.target = target;
    }

    /**
     * Ignores the locator: the replay has no source locations.
     *
     * @param locator the locator
     */
    @Override
    public void setDocumentLocator(final Locator locator) {
        // Not passed on
    }

    /**
     * Passes the document start on.
     *
     * @throws SAXException if the target fails
     */
    @Override
    public void startDocument() throws SAXException {
        this.target.startDocument();
    }

    /**
     * Passes the document end on.
     *
     * @throws SAXException if the target fails
     */
    @Override
    public void endDocument() throws SAXException {
        this.target.endDocument();
    }

    /**
     * Records a declaration for the next element.
     *
     * @param prefix the prefix
     * @param uri    the namespace URI
     */
    @Override
    public void startPrefixMapping(final String prefix, final String uri) {
        this.pending.add(new String[] { prefix, uri });
    }

    /**
     * Ignored: the replay declares its own.
     *
     * @param prefix the prefix
     */
    @Override
    public void endPrefixMapping(final String prefix) {
        // The replay declares its own
    }

    /**
     * Records an element start.
     *
     * @param uri       namespace URI
     * @param localName local name
     * @param qName     qualified name
     * @param atts      attributes
     */
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) {
        final Element e = new Element(uri, localName, qName, this.pending, atts);
        this.pending = new ArrayList<>();
        if (this.open.isEmpty()) {
            this.root = e;
        } else {
            this.open.peek().children.add(e);
        }
        this.open.push(e);
    }

    /**
     * Records an element end; at the root's end, replays the whole element to the target.
     *
     * @param uri       namespace URI
     * @param localName local name
     * @param qName     qualified name
     * @throws SAXException if the target fails
     */
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.open.pop();
        if (this.open.isEmpty()) {
            final Set<String> valuePrefixes = new HashSet<>();
            collectValuePrefixes(this.root, valuePrefixes);
            this.replay(this.root, valuePrefixes, new ArrayDeque<>());
            this.root = null;
        }
    }

    /**
     * Records text.
     *
     * @param ch     characters
     * @param start  start offset
     * @param length length
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) {
        if (this.open.isEmpty()) {
            return;
        }
        final List<Object> children = this.open.peek().children;
        if (!children.isEmpty() && children.get(children.size() - 1) instanceof StringBuilder sb) {
            sb.append(ch, start, length);
        } else {
            children.add(new StringBuilder().append(ch, start, length));
        }
    }

    /**
     * Records whitespace as text.
     *
     * @param ch     characters
     * @param start  start offset
     * @param length length
     */
    @Override
    public void ignorableWhitespace(final char[] ch, final int start, final int length) {
        this.characters(ch, start, length);
    }

    /**
     * Ignored: JAXB writes none.
     *
     * @param target the PI target
     * @param data   the PI data
     */
    @Override
    public void processingInstruction(final String target, final String data) {
        // JAXB writes none
    }

    /**
     * Ignored: JAXB writes none.
     *
     * @param name the entity name
     */
    @Override
    public void skippedEntity(final String name) {
        // JAXB writes none
    }

    private static String valuePrefix(final String value) {
        final Matcher m = VALUE_PREFIX.matcher(value);
        return m.find() ? m.group(1) : null;
    }

    private static void collectValuePrefixes(final Element e, final Set<String> into) {
        for (int i = 0; i < e.attributes.getLength(); i++) {
            final String p = valuePrefix(e.attributes.getValue(i));
            if (p != null) {
                into.add(p);
            }
        }
        for (final Object c : e.children) {
            if (c instanceof Element child) {
                collectValuePrefixes(child, into);
            } else {
                final String p = valuePrefix(c.toString());
                if (p != null) {
                    into.add(p);
                }
            }
        }
    }

    private static String prefixOf(final String qName) {
        final int i = (qName != null) ? qName.indexOf(':') : -1;
        return (i > 0) ? qName.substring(0, i) : "";
    }

    private static String qualify(final String prefix, final String localName) {
        return prefix.isEmpty() ? localName : prefix + ":" + localName;
    }

    /**
     * The URI a prefix is bound to in what has been written.
     *
     * @param written the written declarations, innermost first
     * @param prefix  the prefix
     * @return the URI, "" for an unbound default namespace, or null for an unbound prefix
     */
    private static String boundTo(final Deque<Map<String, String>> written, final String prefix) {
        for (final Map<String, String> m : written) {
            final String uri = m.get(prefix);
            if (uri != null) {
                return uri;
            }
        }
        return prefix.isEmpty() ? "" : null;
    }

    private void replay(final Element e, final Set<String> valuePrefixes, final Deque<Map<String, String>> written)
        throws SAXException
    {
        final Map<String, String> declared = new LinkedHashMap<>();
        final boolean qualified = e.uri != null && !e.uri.isEmpty();
        final String prefix = qualified ? prefixOf(e.qName) : "";
        if (qualified) {
            declared.put(prefix, e.uri);
        }
        for (final String[] d : e.declarations) {
            if (valuePrefixes.contains(d[0])) {
                declared.put(d[0], d[1]);
            }
        }
        final AttributesImpl atts = new AttributesImpl();
        for (int i = 0; i < e.attributes.getLength(); i++) {
            final String auri = e.attributes.getURI(i);
            final String local = e.attributes.getLocalName(i);
            if (auri != null && !auri.isEmpty()) {
                final String p = prefixOf(e.attributes.getQName(i));
                declared.put(p, auri);
                atts.addAttribute(auri, local, qualify(p, local), e.attributes.getType(i), e.attributes.getValue(i));
            } else {
                atts.addAttribute("", local, local, e.attributes.getType(i), e.attributes.getValue(i));
            }
        }

        // Written: those not already in scope with the same binding, and an unqualified element's empty default namespace
        final Map<String, String> writtenHere = new LinkedHashMap<>();
        for (final Map.Entry<String, String> d : declared.entrySet()) {
            if (!d.getValue().equals(boundTo(written, d.getKey()))) {
                writtenHere.put(d.getKey(), d.getValue());
            }
        }
        if (!qualified && !"".equals(boundTo(written, ""))) {
            writtenHere.put("", "");
        }
        for (final Map.Entry<String, String> d : writtenHere.entrySet()) {
            this.target.startPrefixMapping(d.getKey(), d.getValue());
        }
        written.push(writtenHere.isEmpty() ? Collections.<String, String>emptyMap() : writtenHere);

        final String uri = qualified ? e.uri : "";
        final String qName = qualify(prefix, e.localName);
        this.target.startElement(uri, e.localName, qName, atts);
        for (final Object c : e.children) {
            if (c instanceof Element child) {
                this.replay(child, valuePrefixes, written);
            } else {
                final char[] text = c.toString().toCharArray();
                this.target.characters(text, 0, text.length);
            }
        }
        this.target.endElement(uri, e.localName, qName);

        written.pop();
        for (final String p : writtenHere.keySet()) {
            this.target.endPrefixMapping(p);
        }
    }
}
