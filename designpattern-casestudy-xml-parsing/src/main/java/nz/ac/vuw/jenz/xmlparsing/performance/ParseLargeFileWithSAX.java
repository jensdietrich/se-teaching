package nz.ac.vuw.jenz.xmlparsing.performance;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Script to parse the large generated file with a DOM parser.
 * Runtime is reported on the console, for memory consumption monitor application with jvisualvm or a similar tool.
 * @author jens dietrich
 */
public class ParseLargeFileWithSAX {

    public static void main (String[] args) throws Exception {

        Thread.sleep(10_000);  // 10 s time to connect jvisualvm to running application
        long startTime = System.currentTimeMillis();

        InputSource input = new InputSource(CreateLargeXMLFile.getLargeFile().getAbsolutePath());
        ExtractRecipients handler = new ExtractRecipients();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(input, handler);
        List<String> recipients = handler.getRecipients();
        
        long endTime = System.currentTimeMillis();

        System.out.println("Parsing done, " + recipients.size() + " recipients extracted");
        System.out.println("This took " + (endTime - startTime) + " ms");
    }

}
