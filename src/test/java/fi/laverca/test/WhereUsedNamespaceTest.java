package fi.laverca.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import fi.laverca.util.JMarshallerFactory;
import fi.laverca.util.WhereUsedNamespaceHandler;

/**
 * Namespaces declared where first used, as Axis1 did, while streaming. The input is fed as JAXB RI feeds it: every
 * namespace of its context declared before the root.
 */
class WhereUsedNamespaceTest {

    private static final String A   = "urn:test:a";
    private static final String B   = "urn:test:b";
    private static final String C   = "urn:test:unused";
    private static final String I   = "urn:test:including";
    private static final String D   = "urn:test:included";
    private static final String XSI = "http://www.w3.org/2001/XMLSchema-instance";

    private StringWriter out;
    private WhereUsedNamespaceHandler h;

    @BeforeAll
    static void inclusions() {
        // Test namespaces only, so other tests' output is unchanged
        JMarshallerFactory.registerPrefix("ti", I);
        JMarshallerFactory.registerPrefix("td", D);
        JMarshallerFactory.registerPrefixInclusion("ti", "td");
    }

    @BeforeEach
    void setUp() throws Exception {
        final TransformerHandler th = ((SAXTransformerFactory) TransformerFactory.newInstance()).newTransformerHandler();
        th.getTransformer().setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        this.out = new StringWriter();
        th.setResult(new StreamResult(this.out));
        this.h = new WhereUsedNamespaceHandler(th);
        this.h.startDocument();
        this.h.startPrefixMapping("a", A);
        this.h.startPrefixMapping("b", B);
        this.h.startPrefixMapping("c", C);
        this.h.startPrefixMapping("ti", I);
        this.h.startPrefixMapping("xsi", XSI);
    }

    private String end() throws SAXException {
        this.h.endDocument();
        return this.out.toString();
    }

    private void start(final String uri, final String qName) throws SAXException {
        this.start(uri, qName, new AttributesImpl());
    }

    private void start(final String uri, final String qName, final Attributes atts) throws SAXException {
        final int i = qName.indexOf(':');
        this.h.startElement(uri, (i < 0) ? qName : qName.substring(i + 1), qName, atts);
    }

    private void end(final String uri, final String qName) throws SAXException {
        final int i = qName.indexOf(':');
        this.h.endElement(uri, (i < 0) ? qName : qName.substring(i + 1), qName);
    }

    private void text(final String s) throws SAXException {
        this.h.characters(s.toCharArray(), 0, s.length());
    }

    private static AttributesImpl xsiType(final String value) {
        final AttributesImpl atts = new AttributesImpl();
        atts.addAttribute(XSI, "type", "xsi:type", "CDATA", value);
        return atts;
    }

    @Test
    void unusedNamespacesAreNotDeclared() throws Exception {
        this.start(A, "a:Root");
        this.start(A, "a:Child");
        this.text("x");
        this.end(A, "a:Child");
        this.end(A, "a:Root");
        assertEquals("<a:Root xmlns:a=\"urn:test:a\"><a:Child>x</a:Child></a:Root>", this.end());
    }

    @Test
    void declaredOnceOnTheElementHoldingAllUses() throws Exception {
        this.start(A, "a:Root");
        this.start(B, "b:Part");
        this.start(B, "b:Item");
        this.end(B, "b:Item");
        this.start(B, "b:Item");
        this.end(B, "b:Item");
        this.end(B, "b:Part");
        this.end(A, "a:Root");
        assertEquals("<a:Root xmlns:a=\"urn:test:a\"><b:Part xmlns:b=\"urn:test:b\"><b:Item/><b:Item/></b:Part></a:Root>", this.end());
    }

    @Test
    void siblingsRepeatTheDeclaration() throws Exception {
        this.start("", "Wrapper");
        this.start(A, "a:One");
        this.end(A, "a:One");
        this.start(A, "a:Two");
        this.end(A, "a:Two");
        this.end("", "Wrapper");
        assertEquals("<Wrapper><a:One xmlns:a=\"urn:test:a\"/><a:Two xmlns:a=\"urn:test:a\"/></Wrapper>", this.end());
    }

    @Test
    void xsiTypeValueDeclaredOnItsElement() throws Exception {
        this.start(A, "a:Root");
        this.start(A, "a:Value", xsiType("b:Derived"));
        this.end(A, "a:Value");
        this.end(A, "a:Root");
        assertEquals("<a:Root xmlns:a=\"urn:test:a\"><a:Value xmlns:xsi=\"" + XSI + "\" xmlns:b=\"urn:test:b\" xsi:type=\"b:Derived\"/></a:Root>",
                     this.end());
    }

    @Test
    void textQNameDeclaredOnItsElement() throws Exception {
        this.start(A, "a:Fault");
        this.start("", "faultcode");
        this.text("b:Server");
        this.end("", "faultcode");
        this.end(A, "a:Fault");
        assertEquals("<a:Fault xmlns:a=\"urn:test:a\"><faultcode xmlns:b=\"urn:test:b\">b:Server</faultcode></a:Fault>", this.end());
    }

    @Test
    void textThatIsNoQNameDeclaresNothing() throws Exception {
        this.start(A, "a:Root");
        this.text("http://example.com/x");
        this.end(A, "a:Root");
        assertEquals("<a:Root xmlns:a=\"urn:test:a\">http://example.com/x</a:Root>", this.end());
    }

    @Test
    void inclusionDeclaredWithItsNamespace() throws Exception {
        this.start("", "Wrapper");
        this.start(I, "ti:Info");
        this.end(I, "ti:Info");
        this.end("", "Wrapper");
        assertEquals("<Wrapper><ti:Info xmlns:ti=\"" + I + "\" xmlns:td=\"" + D + "\"/></Wrapper>", this.end());
    }

    @Test
    void inclusionDeclaredWithAValuePrefix() throws Exception {
        this.start(A, "a:Root");
        this.start(A, "a:Value", xsiType("ti:Type"));
        this.end(A, "a:Value");
        this.end(A, "a:Root");
        assertEquals("<a:Root xmlns:a=\"urn:test:a\"><a:Value xmlns:xsi=\"" + XSI + "\" xmlns:ti=\"" + I + "\" xmlns:td=\"" + D
                     + "\" xsi:type=\"ti:Type\"/></a:Root>", this.end());
    }
}
