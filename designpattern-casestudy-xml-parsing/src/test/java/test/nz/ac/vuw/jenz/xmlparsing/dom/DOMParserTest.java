package test.nz.ac.vuw.jenz.xmlparsing.dom;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertSame;

/**
 * Simple example showing how to use a DOM parser
 * to extract the recipients names from the XML document.
 * @author Jens Dietrich
 */
public class DOMParserTest {

	@Test
	public void test() throws Exception {
		File xmlFile = new File("src/test/resources/email.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);

		List<String> recipients = new ArrayList<>();
		Element root = (Element)doc.getElementsByTagName("email").item(0);
		NodeList toNodes = root.getElementsByTagName("to");
		for (int i=0;i<toNodes.getLength();i++) {
			Element toElement = (Element)toNodes.item(i);
			Element displayNameElement = (Element)toElement.getElementsByTagName("display_name").item(0);
			String displayName = displayNameElement.getTextContent();
			recipients.add(displayName);
		}

		assertTrue(recipients.contains("Tim"));
		assertTrue(recipients.contains("Tom"));
		assertSame(2,recipients.size());
	}


}
