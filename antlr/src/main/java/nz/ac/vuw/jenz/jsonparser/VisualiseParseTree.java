package nz.ac.vuw.jenz.jsonparser;

import nz.ac.vuw.jenz.antr.util.*;
import nz.ac.vuw.jenz.jsonparser.parser.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;
import java.net.URL;

/**
 * Visualises the parse tree. From  https://stackoverflow.com/questions/49413911/antlr4-parse-tree-to-dot-using-dotgenerator .
 * The antlr tools (such as the intellij plugin can also visualise the parse tree (View > Tools > antlr).
 * @author jens dietrich
 */
public class VisualiseParseTree {
    public static void main(String[] args) throws Exception {
        URL url = JSON2XML.class.getResource("/input.json");
        JSONLexer lexer = new JSONLexer(CharStreams.fromFileName(url.getFile()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        SimpleTree tree = new SimpleTree.Builder()
                .withParser(parser)
                .withParseTree(parser.json())
                .withDisplaySymbolicName(false)
                .build();

        DotOptions options = new DotOptions.Builder()
                .withParameters("  labelloc=\"t\";\n  label=\"Expression Tree\";\n\n")
                .withLexerRuleShape("circle")
                .build();

        String treeAsDot = (new DotTreeRepresentation().display(tree, options));
        File dotFile = new File("json.dot");
        try (PrintWriter out = new PrintWriter(new FileWriter(dotFile))) {
            out.println(treeAsDot);
        }

        System.out.println("dot representing parse tree created: " + dotFile.getAbsolutePath());

        Dot2Png.convert(dotFile);
        Dot2Svg.convert(dotFile);

    }
}
