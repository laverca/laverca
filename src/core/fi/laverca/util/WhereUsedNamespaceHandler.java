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
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * SAX filter that declares each namespace on the element that first uses it, as Axis1's serializer did, instead of every
 * namespace of the JAXB context on the root (JAXB RI declares all of them before the first element).
 * <p>
 * Streaming, without look-ahead: an element declares what its name and attributes use, and the prefix a QName attribute
 * value (e.g. an xsi:type) or its text starts with, unless already in scope with the same binding; siblings may repeat a
 * declaration. Only the pending start tag is held, until the next event, for a QName in the text. Prefixes are the ones
 * JAXB chose from {@link JMarshallerFactory}'s prefix mapper; a declared namespace also declares its registered
 * inclusions ({@link JMarshallerFactory#registerPrefixInclusion(String, String...)}).
 */
public final class WhereUsedNamespaceHandler implements ContentHandler {

    private static final Pattern VALUE_PREFIX = Pattern.compile("^\\s*([A-Za-z_][\\w.-]*):");

    /** A start tag; held until the next event, as its text may still add a declaration */
    private static final class StartTag {
        final String uri;
        final String localName;
        final String qName;
        final Attributes attributes;
        final Map<String, String> writtenHere; // prefix -> URI, declared on this element

        StartTag(final String uri, final String localName, final String qName, final Attributes attributes,
                 final Map<String, String> writtenHere) {
            this.uri = uri;
            this.localName = localName;
            this.qName = qName;
            this.attributes = attributes;
            this.writtenHere = writtenHere;
        }
    }

    private final ContentHandler target;

    /** The input's declarations, innermost first, prefix to URI: what a QName value's prefix means */
    private final Deque<Map<String, String>> fed = new ArrayDeque<>();
    private Map<String, String> pendingFed = new HashMap<>();

    /** The open elements, innermost first */
    private final Deque<StartTag> open = new ArrayDeque<>();

    /** The open element whose start tag isn't passed on yet, or null */
    private StartTag held;

    /**
     * Creates the filter.
     *
     * @param target the handler to pass the document on to
     */
    public WhereUsedNamespaceHandler(final ContentHandler target) {
        this.target = target;
    }

    /**
     * Ignores the locator: the output's locations differ.
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
     * Records an input declaration for the next element.
     *
     * @param prefix the prefix
     * @param uri    the namespace URI
     */
    @Override
    public void startPrefixMapping(final String prefix, final String uri) {
        this.pendingFed.put(prefix, uri);
    }

    /**
     * Ignored: declarations are chosen per element.
     *
     * @param prefix the prefix
     */
    @Override
    public void endPrefixMapping(final String prefix) {
        // Declarations are chosen per element
    }

    /**
     * Holds an element start with the declarations its name, attributes and QName attribute values need.
     *
     * @param uri       namespace URI
     * @param localName local name
     * @param qName     qualified name
     * @param atts      attributes
     * @throws SAXException if the target fails
     */
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        this.flush();
        this.fed.push(this.pendingFed.isEmpty() ? Collections.<String, String>emptyMap() : this.pendingFed);
        this.pendingFed = new HashMap<>();

        final Map<String, String> declared = new LinkedHashMap<>();
        final boolean qualified = uri != null && !uri.isEmpty();
        final String prefix = qualified ? prefixOf(qName) : "";
        if (qualified) {
            declare(prefix, uri, declared);
        }
        final AttributesImpl out = new AttributesImpl();
        for (int i = 0; i < atts.getLength(); i++) {
            final String auri = atts.getURI(i);
            final String local = atts.getLocalName(i);
            if (auri != null && !auri.isEmpty()) {
                final String p = prefixOf(atts.getQName(i));
                declare(p, auri, declared);
                out.addAttribute(auri, local, qualify(p, local), atts.getType(i), atts.getValue(i));
            } else {
                out.addAttribute("", local, local, atts.getType(i), atts.getValue(i));
            }
        }
        for (int i = 0; i < atts.getLength(); i++) {
            this.declareValuePrefix(atts.getValue(i), declared);
        }

        // Declared on this element: those not already in scope with the same binding, and an unqualified element's empty default namespace
        final Map<String, String> writtenHere = new LinkedHashMap<>();
        for (final Map.Entry<String, String> d : declared.entrySet()) {
            if (!d.getValue().equals(this.boundTo(d.getKey()))) {
                writtenHere.put(d.getKey(), d.getValue());
            }
        }
        if (!qualified && !"".equals(this.boundTo(""))) {
            writtenHere.put("", "");
        }
        this.held = new StartTag(qualified ? uri : "", localName, qualify(prefix, localName), out, writtenHere);
        this.open.push(this.held);
    }

    /**
     * Passes an element end on.
     *
     * @param uri       namespace URI
     * @param localName local name
     * @param qName     qualified name
     * @throws SAXException if the target fails
     */
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.flush();
        final StartTag tag = this.open.pop();
        this.target.endElement(tag.uri, tag.localName, tag.qName);
        for (final String p : tag.writtenHere.keySet()) {
            this.target.endPrefixMapping(p);
        }
        this.fed.pop();
    }

    /**
     * Passes text on; a QName at the start of an element's text (e.g. a fault code) is declared on that element first.
     *
     * @param ch     characters
     * @param start  start offset
     * @param length length
     * @throws SAXException if the target fails
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (this.held != null) {
            final Map<String, String> more = new LinkedHashMap<>();
            this.declareValuePrefix(new String(ch, start, length), more);
            for (final Map.Entry<String, String> d : more.entrySet()) {
                if (!d.getValue().equals(this.boundTo(d.getKey()))) {
                    this.held.writtenHere.put(d.getKey(), d.getValue());
                }
            }
            this.flush();
        }
        this.target.characters(ch, start, length);
    }

    /**
     * Passes whitespace on as text.
     *
     * @param ch     characters
     * @param start  start offset
     * @param length length
     * @throws SAXException if the target fails
     */
    @Override
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
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

    /**
     * Passes the held start tag on.
     *
     * @throws SAXException if the target fails
     */
    private void flush() throws SAXException {
        final StartTag tag = this.held;
        if (tag == null) {
            return;
        }
        this.held = null;
        for (final Map.Entry<String, String> d : tag.writtenHere.entrySet()) {
            this.target.startPrefixMapping(d.getKey(), d.getValue());
        }
        this.target.startElement(tag.uri, tag.localName, tag.qName, tag.attributes);
    }

    /**
     * Adds the declaration a QName value's prefix refers to in the input, unless the element already declares that prefix.
     *
     * @param value    the value
     * @param declared the element's declarations
     */
    private void declareValuePrefix(final String value, final Map<String, String> declared) {
        final Matcher m = VALUE_PREFIX.matcher(value);
        if (!m.find() || declared.containsKey(m.group(1))) {
            return;
        }
        final String uri = this.fedUri(m.group(1));
        if (uri != null) {
            declare(m.group(1), uri, declared);
        }
    }

    /**
     * Adds a declaration and its registered inclusions.
     *
     * @param prefix   the prefix
     * @param uri      the namespace URI
     * @param declared the element's declarations
     */
    private static void declare(final String prefix, final String uri, final Map<String, String> declared) {
        declared.put(prefix, uri);
        for (final Map.Entry<String, String> inc : JMarshallerFactory.inclusionsOf(uri).entrySet()) {
            declared.putIfAbsent(inc.getKey(), inc.getValue());
        }
    }

    /**
     * The URI a prefix is bound to in the input.
     *
     * @param prefix the prefix
     * @return the URI, or null when the input doesn't declare it
     */
    private String fedUri(final String prefix) {
        for (final Map<String, String> m : this.fed) {
            final String uri = m.get(prefix);
            if (uri != null) {
                return uri;
            }
        }
        return null;
    }

    /**
     * The URI a prefix is bound to in what has been declared, the held start tag included.
     *
     * @param prefix the prefix
     * @return the URI, "" for an unbound default namespace, or null for an unbound prefix
     */
    private String boundTo(final String prefix) {
        for (final StartTag t : this.open) {
            final String uri = t.writtenHere.get(prefix);
            if (uri != null) {
                return uri;
            }
        }
        return prefix.isEmpty() ? "" : null;
    }

    private static String prefixOf(final String qName) {
        final int i = (qName != null) ? qName.indexOf(':') : -1;
        return (i > 0) ? qName.substring(0, i) : "";
    }

    private static String qualify(final String prefix, final String localName) {
        return prefix.isEmpty() ? localName : prefix + ":" + localName;
    }
}
