package test.nz.ac.vuw.jenz.xmlparsing.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Content handler implementation which simply logs SAX events on the console.
 * toMode is used to show the complexities of handing context and state
 * @author Jens Dietrich
 */
public class ExtractRecipients extends DefaultHandler {

	private boolean toMode = false;
	private boolean displayNameMode = false;
	private List<String> recipients = new ArrayList<String>();

	public List<String> getRecipients() {
		return recipients;
	}

	@Override
	public void endDocument() throws SAXException {}

	@Override
	public void startDocument() throws SAXException {}

	/**
	 * Make sure to only record names if we are in the correct tag !
	 * @param ch
	 * @param start
	 * @param length
	 * @throws SAXException
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (displayNameMode) {
			char[] data = new char[length];
			for (int i=start;i<(start+length);i++) data[i-start]=ch[i];
			recipients.add(new String(data));
		}
	}

	@Override
	public void startElement(String namespaceURI,String localName,String qName,Attributes atts) throws SAXException {
		if ("to".equals(qName)) {
			toMode=true;
		}
		else if (toMode && "display_name".equals(qName)) {
			displayNameMode=true;
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (qName.equals("to")) {
			toMode=false;
		}
		else if (toMode && "display_name".equals(qName)) {
			displayNameMode=false;
		}
	}


}
