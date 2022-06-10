package nz.ac.vuw.jenz.csv;

import nz.ac.vuw.jenz.antr.util.Dot2Png;
import nz.ac.vuw.jenz.antr.util.DotOptions;
import nz.ac.vuw.jenz.antr.util.DotTreeRepresentation;
import nz.ac.vuw.jenz.antr.util.SimpleTree;
import nz.ac.vuw.jenz.csv.parser.CSVLexer;
import nz.ac.vuw.jenz.csv.parser.CSVParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Visualises the AST.
 * The antlr tools (such as the intellij plugin can also visualise the AST (View > Tools > antlr).
 * @author jens dietrich
 */
public class PrintTokens {
    public static void main(String[] args) throws Exception {

        URL url = PrintTokens.class.getResource("/input.csv");
        CSVLexer lexer = new CSVLexer(CharStreams.fromFileName(url.getFile()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // stream is lazy , need to parse input to create tokens
        new CSVParser(tokens).csv();

        for (Token token:tokens.getTokens()) {
            System.out.println(token);
        }
    }
}
