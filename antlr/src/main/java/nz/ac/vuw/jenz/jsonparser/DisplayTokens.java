package nz.ac.vuw.jenz.jsonparser;

import nz.ac.vuw.jenz.jsonparser.parser.JSONLexer;
import nz.ac.vuw.jenz.jsonparser.parser.JSONListener;
import nz.ac.vuw.jenz.jsonparser.parser.JSONParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.PrintStream;
import java.net.URL;

/**
 * Print out the tokens produced by the lexer.
 * @author jens dietrich
 */
public class DisplayTokens {

    public static void main(String[] args) throws Exception {
        URL url = JSON2XML.class.getResource("/input.json");
        JSONLexer lexer = new JSONLexer(CharStreams.fromFileName(url.getFile()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        System.out.println("Number of tokens expected: " + tokens.getNumberOfOnChannelTokens());
        System.out.println("Listing tokens (one per line): ");
        for (Token token:tokens.getTokens()) {
            System.out.println("\ttoken text: \"" + token.getText() + "\" , token type: \""+ token.getType() + "\"");
        }
    }
}