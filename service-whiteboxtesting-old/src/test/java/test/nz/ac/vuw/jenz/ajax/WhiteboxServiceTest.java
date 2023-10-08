package test.nz.ac.vuw.jenz.ajax;

import nz.ac.vuw.jenz.ajax.FirstNameService;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WhiteboxServiceTest {

    @Test
    public void testInvalidRequestResponseCode1() throws IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        // query parameter missing
        FirstNameService service = new FirstNameService();
        service.doGet(request,response);

        assertEquals(400,response.getStatus());
    }

    @Test
    public void testInvalidRequestResponseCode2() throws IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("not a valid param name","42");
        MockHttpServletResponse response = new MockHttpServletResponse();
        // wrong query parameter

        FirstNameService service = new FirstNameService();
        service.doGet(request,response);

        assertEquals(400,response.getStatus());
    }

    @Test
    public void testValidRequestResponseCode() throws IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name","J");
        MockHttpServletResponse response = new MockHttpServletResponse();

        FirstNameService service = new FirstNameService();
        service.doGet(request,response);

        assertEquals(200,response.getStatus());
    }

    @Test
    public void testValidContentType() throws IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name","J");
        MockHttpServletResponse response = new MockHttpServletResponse();

        FirstNameService service = new FirstNameService();
        service.doGet(request,response);

        assertTrue(response.getContentType().startsWith("text/plain"));
    }

    @Test
    public void testReturnedValues() throws IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name","J");
        MockHttpServletResponse response = new MockHttpServletResponse();

        FirstNameService service = new FirstNameService();
        service.doGet(request,response);

        String result = response.getContentAsString();
        String[] names = result.split(" ");
        Set<String> set = Arrays.stream(names).collect(Collectors.toSet());

        assertTrue(set.contains("Joshua"));
        assertTrue(set.contains("Jason"));
        assertTrue(set.contains("Jasmine"));
    }

}
