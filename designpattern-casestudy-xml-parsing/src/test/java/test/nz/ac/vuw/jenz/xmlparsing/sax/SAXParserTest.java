package test.nz.ac.vuw.jenz.xmlparsing.sax;

import org.xml.sax.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertSame;

/**
 * Simple example showing how to use a SAX Parser to extract the recipients names from the XML document.
 * Most logic is in the ExtractRecipients handler class.
 * @author Jens Dietrich
 */
public class SAXParserTest {

	@org.junit.Test
	public void test() throws Exception {
		InputSource input = new InputSource("src/test/resources/email.xml");

		ExtractRecipients handler = new ExtractRecipients();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(input, handler);
		List<String> recipients = handler.getRecipients();

		assertTrue(recipients.contains("Tim"));
		assertTrue(recipients.contains("Tom"));
		assertSame(2,recipients.size());
	}
}
