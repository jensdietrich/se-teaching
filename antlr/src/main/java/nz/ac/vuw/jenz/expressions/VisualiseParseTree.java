package nz.ac.vuw.jenz.expressions;

import com.google.common.base.Preconditions;
import nz.ac.vuw.jenz.antr.util.*;
import nz.ac.vuw.jenz.expressions.parser.ArithmeticExpressionLexer;
import nz.ac.vuw.jenz.expressions.parser.ArithmeticExpressionParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Visualises the parse tree.
 * The antlr tools (such as the intellij plugin can also visualise the parse tree (View > Tools > antlr).
 * @author jens dietrich
 */
public class VisualiseParseTree {
    public static void main(String[] args) throws Exception {

        Preconditions.checkArgument(args.length==2,"two parameters requires: an expression to be checked (string) and a file name for the generated figure in dot format");
        String expression = args[0];
        ArithmeticExpressionLexer lexer = new ArithmeticExpressionLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArithmeticExpressionParser parser = new ArithmeticExpressionParser(tokens);
        SimpleTree tree = new SimpleTree.Builder()
            .withParser(parser)
            .withParseTree(parser.start())
            .withDisplaySymbolicName(false)
            .build();

        DotOptions options = new DotOptions.Builder()
            .withParameters("  labelloc=\"t\";\n  label=\"Expression Tree\";\n\n")
            .withLexerRuleShape("circle")
            .build();

        String treeAsDot = (new DotTreeRepresentation().display(tree, options));
        File dotFile = new File(args[1]);
        try (PrintWriter out = new PrintWriter(new FileWriter(dotFile))) {
            out.println(treeAsDot);
        }

        System.out.println("dot representing parse tree created: " + dotFile.getAbsolutePath());

        Dot2Png.convert(dotFile);
        Dot2Svg.convert(dotFile);

    }
}
