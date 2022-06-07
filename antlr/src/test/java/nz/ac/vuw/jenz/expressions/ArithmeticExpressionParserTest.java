package nz.ac.vuw.jenz.expressions;

import nz.ac.vuw.jenz.expressions.parser.ArithmeticExpressionLexer;
import nz.ac.vuw.jenz.expressions.parser.ArithmeticExpressionParser;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArithmeticExpressionParserTest {

    private boolean isValid(String input)  {
        try {
            ArithmeticExpressionLexer lexer = new ArithmeticExpressionLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ArithmeticExpressionParser parser = new ArithmeticExpressionParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            parser.start();
            return true;
        }
        catch (ParseCancellationException x) {
            return false;
        }
    }
    @Test
    public void testValid1 () {
        assertTrue(isValid("1"));
    }

    @Test
    public void testValid2 () {
        assertTrue(isValid("x"));
    }

    @Test
    public void testValid3 () {
        assertTrue(isValid("x+1"));
    }

    @Test
    public void testValid4 () {
        assertTrue(isValid("x-1"));
    }

    @Test
    public void testValid5 () {
        assertTrue(isValid("x*1"));
    }

    @Test
    public void testValid6 () {
        assertTrue(isValid("x/1"));
    }

    @Test
    public void testValid7 () {
        assertTrue(isValid("x+y/3"));
    }

    @Test
    public void testValid8 () {
        assertTrue(isValid("(x+y)/(z+3)"));
    }

}
