package nz.ac.vuw.jenz.jsonparser;

import nz.ac.vuw.jenz.jsonparser.parser.*;
import nz.ac.vuw.jenz.antr.util.DotOptions;
import nz.ac.vuw.jenz.antr.util.DotTreeRepresentation;
import nz.ac.vuw.jenz.antr.util.SimpleTree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;
import java.net.URL;

/**
 * Visualises the AST. From  https://stackoverflow.com/questions/49413911/antlr4-parse-tree-to-dot-using-dotgenerator .
 * Creates a dot file that can then be transformed into svg, png , .. using with graphvis, for instance:
 * dot -Tpng:cairo ast.dot -o ast.png
 * dot -Tsvg ast.dot -o ast.svg
 * The antlr tools (such as the intellij plugin can also visualise the AST but were unstable at the time of writing this.
 * @author jens dietrich
 */
public class VisualiseAST {
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
        File dotFile = new File("ast.dot");
        try (PrintWriter out = new PrintWriter(new FileWriter(dotFile))) {
            out.println(treeAsDot);
        }

    }
}
