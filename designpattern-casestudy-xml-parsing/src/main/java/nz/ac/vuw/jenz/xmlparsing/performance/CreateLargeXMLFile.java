package nz.ac.vuw.jenz.xmlparsing.performance;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Random;

/**
 * Script to create a large XML file to use for parsing.
 * With size set to 5_000_000 , the file size is ca 550 MB.
 * @author jens dietrich
 */

public class CreateLargeXMLFile {

    private static final File FILE = new File("email-tmp-xl.xml");
    private static final int SIZE = 5_000_000; // combined to and cc

    // to test
    public static void main (String[] args) throws Exception {
        FILE.delete();
        getLargeFile();
    }

    public static File getLargeFile() throws Exception {
        if (!FILE.exists()) {
            RandomStringGenerator randomStringGenerator =
                new RandomStringGenerator.Builder()
                    .withinRange('0', 'z')
                    .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                    .build();
            Random randomIntGenerator = new Random();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("email");
            doc.appendChild(rootElement);

            for (int i=0;i<SIZE;i++) {
                Element element = null;
                if (randomIntGenerator.nextBoolean()) { // coin flip
                    element = doc.createElement("to");
                }
                else {
                    element = doc.createElement("cc");
                }
                rootElement.appendChild(element);

                Element emailAddress = doc.createElement("email_address");
                emailAddress.setTextContent(randomStringGenerator.generate(5) + "@" + randomStringGenerator.generate(5) + ".com");
                element.appendChild(emailAddress);

                Element displayName = doc.createElement("display_name");
                displayName.setTextContent(randomStringGenerator.generate(10));
                element.appendChild(displayName);

            }

            Element subject = doc.createElement("subject");
            subject.setTextContent("a subject");
            rootElement.appendChild(subject);

            Element body = doc.createElement("body");
            subject.setTextContent("a body");
            rootElement.appendChild(body);

            // write to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // next two lines are for pretty printing
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(FILE);
            transformer.transform(source, result);

            System.out.println("Synthetic data written to " + FILE.getAbsolutePath());

        }

        return FILE;

    }
}
