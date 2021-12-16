package test.nz.ac.vuw.jenz;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import nz.ac.vuw.jenz.jaxb.generated.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * Test whether the jaxb-generated parser works correctly. 
 * Run this with junit. 
 * @author jens dietrich
 */
public class TestParser {

	@Test
	public void test() throws Exception {

		JAXBContext jc = JAXBContext.newInstance( "nz.ac.vuw.jenz.jaxb.generated" );
		Unmarshaller parser = jc.createUnmarshaller();
		File file = new File("email1.xml");
		Email mail = (Email) parser.unmarshal(file);
		
		assertEquals(1,mail.getTo().size());
		Participant to = mail.getTo().get(0);
		assertEquals("jenz@vuw.ac.nz",to.getEmailAddress());
		assertEquals("Jens Dietrich",to.getDisplayName());
		
		assertEquals(1,mail.getCc().size());
		Participant cc = mail.getCc().get(0);
		assertEquals("studentsSWEN301@vuw.ac.nz",cc.getEmailAddress());
		assertEquals("SWEN301 student list",cc.getDisplayName());
		
		assertEquals(0,mail.getBcc().size());
		
		assertEquals("update",mail.getSubject());
		assertEquals("this lecture notes have been updated",mail.getBody());
		
	}

}
