package nz.ac.vuw.jenz.csvplus;


import nz.ac.vuw.jenz.csvplus.parser.CSVPlusLexer;
import nz.ac.vuw.jenz.csvplus.parser.CSVPlusParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.net.URL;

/**
 * Visualises the AST.
 * The antlr tools (such as the intellij plugin can also visualise the AST (View > Tools > antlr).
 * @author jens dietrich
 */
public class PrintTokens {
    public static void main(String[] args) throws Exception {

        URL url = PrintTokens.class.getResource("/input2.csv");
        CSVPlusLexer lexer = new CSVPlusLexer(CharStreams.fromFileName(url.getFile()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // stream is lazy , need to parse input to create tokens
        new CSVPlusParser(tokens).csv();

        for (Token token:tokens.getTokens()) {
            System.out.println(token);
        }
    }
}
