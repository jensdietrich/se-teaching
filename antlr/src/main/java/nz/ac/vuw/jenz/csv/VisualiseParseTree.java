package nz.ac.vuw.jenz.csv;

import nz.ac.vuw.jenz.antr.util.*;
import nz.ac.vuw.jenz.csv.parser.CSVLexer;
import nz.ac.vuw.jenz.csv.parser.CSVParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Visualises the parse tree.
 * The antlr tools (such as the intellij plugin can also visualise the parse tree (View > Tools > antlr).
 * @author jens dietrich
 */
public class VisualiseParseTree {
    public static void main(String[] args) throws Exception {

        URL url = VisualiseParseTree.class.getResource("/input.csv");
        CSVLexer lexer = new CSVLexer(CharStreams.fromFileName(url.getFile()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokens);
        SimpleTree tree = new SimpleTree.Builder()
            .withParser(parser)
            .withParseTree(parser.csv())
            .withDisplaySymbolicName(false)
            .build();

        DotOptions options = new DotOptions.Builder()
            .withParameters("  labelloc=\"t\";\n  label=\"Expression Tree\";\n\n")
            .withLexerRuleShape("circle")
            .build();

        String treeAsDot = (new DotTreeRepresentation().display(tree, options));
        File dotFile = new File("csv.dot");
        try (PrintWriter out = new PrintWriter(new FileWriter(dotFile))) {
            out.println(treeAsDot);
        }

        System.out.println("dot representing parse tree created: " + dotFile.getAbsolutePath());

        Dot2Png.convert(dotFile);
        Dot2Svg.convert(dotFile);

    }
}
