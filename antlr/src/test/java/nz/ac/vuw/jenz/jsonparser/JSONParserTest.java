package nz.ac.vuw.jenz.jsonparser;

import nz.ac.vuw.jenz.jsonparser.parser.JSONLexer;
import nz.ac.vuw.jenz.jsonparser.parser.JSONParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JSONParserTest {

    private boolean isValid(String input)  {
        try {
            JSONLexer lexer = new JSONLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JSONParser parser = new JSONParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            parser.json();
            return true;
        }
        catch (ParseCancellationException x) {
            return false;
        }
    }
    @Test
    public void testEmptyValidObject () {
        assertTrue(isValid("{}"));
    }
    @Test
    public void testEmptyInvalidObject () {
        assertFalse (isValid("**"));
    }
    @Test
    public void testEmptyValidArray1 () {
        assertTrue(isValid("[]"));
    }
    @Test
    public void testEmptyValidArray2 () {
        assertTrue(isValid("[{},{}]"));
    }
    @Test
    public void testEmptyInvalidArray1 () {
        assertFalse(isValid("["));
    }
    @Test
    public void testEmptyInvalidArray2 () {
        assertFalse(isValid("[{}{}]"));
    }
    @Test
    public void testEmptyInvalidArray3 () {
        assertFalse(isValid("[{},}]"));
    }
    @Test
    public void testValidObject1 () {
        assertTrue(isValid("{\"key\":\"value\"}"));
    }

    @Test
    public void testValidObject2 () {
        assertTrue(isValid("{\"key1\":\"value1\",\"key2\":\"value2\"}"));
    }

    @Test
    public void testValidObject3 () {
        assertTrue(isValid("{\"key1\":\"value1\",\"key2\":{}}"));
    }

    @Test
    public void testValidObject4 () {
        assertTrue(isValid("{\"key1\":\"value1\",\"key2\":[]}"));
    }

    @Test
    public void testValidArray () {
        assertTrue(isValid("[{\"key1\":\"value1\"},{\"key2\":\"value2\"}]"));
    }

    @Test
    public void testInvalidObject1 () {
        assertFalse(isValid("{{}:\"value\"}"));
    }

}
