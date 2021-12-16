package test.nz.ac.vuw.jenz.xmlparsing.xpath;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertSame;

/**
 * Simple example showing how to use a Stax Parser to extract the recipients names from the XML document.
 * @author Jens Dietrich
 */
public class XPathTest {

    @Test
	public void test() throws Exception {
		XPathFactory  factory = XPathFactory.newInstance();
		XPath xPath=factory.newXPath();
		InputSource input = new InputSource("src/test/resources/email.xml");
		NodeList matchingNodes = (NodeList) xPath.compile("/email/to/display_name").evaluate(input, XPathConstants.NODESET);

		List<String> recipients = new ArrayList<>();
		for (int i=0;i<matchingNodes.getLength();i++) {
			Element displayNameElement = (Element)matchingNodes.item(i);
			String displayName = displayNameElement.getTextContent();
			recipients.add(displayName);
		}

        assertTrue(recipients.contains("Tim"));
        assertTrue(recipients.contains("Tom"));
        assertSame(2,recipients.size());
	}
}
