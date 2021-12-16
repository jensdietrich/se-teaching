package test.nz.ac.vuw.jenz.xmlparsing.stax;

import org.junit.Test;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertSame;

/**
 * Simple example showing how to use a Stax Parser to extract the recipients names from the XML document.
 * @author Jens Dietrich
 */
public class StaxParserTest {

    @Test
	public void test() throws Exception {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = new FileInputStream("src/test/resources/email.xml");
        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

        boolean toMode = false;
        boolean displayNameMode = false;
        List<String> recipients = new ArrayList<>();
		
        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                String elementName = event.asStartElement().getName().getLocalPart();
                if ("to".equals(elementName)) toMode = true;
                if (toMode && "display_name".equals(elementName)) displayNameMode = true;
            }
            else if (event.isEndElement()) {
                String elementName = event.asEndElement().getName().getLocalPart();
                if ("to".equals(elementName)) toMode = false;
                if (toMode && "display_name".equals(elementName)) displayNameMode = false;
            }
            else if (event.isCharacters() && displayNameMode) {
                recipients.add(event.asCharacters().getData());
            }
        }

        assertTrue(recipients.contains("Tim"));
        assertTrue(recipients.contains("Tom"));
        assertSame(2,recipients.size());

	}
}
