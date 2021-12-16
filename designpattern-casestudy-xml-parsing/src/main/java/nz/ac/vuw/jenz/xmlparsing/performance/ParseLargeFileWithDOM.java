package nz.ac.vuw.jenz.xmlparsing.performance;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Script to parse the large generated file with a DOM parser.
 * Runtime is reported on the console, for memory consumption monitor application with jvisualvm or a similar tool.
 * @author jens dietrich
 */
public class ParseLargeFileWithDOM {

    public static void main (String[] args) throws Exception {

        Thread.sleep(10_000);  // 10 s time to connect jvisualvm to running application
        long startTime = System.currentTimeMillis();

        File xmlFile = CreateLargeXMLFile.getLargeFile();
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

        long endTime = System.currentTimeMillis();

        System.out.println("Parsing done, " + recipients.size() + " recipients extracted");
        System.out.println("This took " + (endTime - startTime) + " ms");
    }

}
