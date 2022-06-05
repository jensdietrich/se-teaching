package nz.ac.vuw.jenz.jsonparser;

import nz.ac.vuw.jenz.jsonparser.parser.JSONLexer;
import nz.ac.vuw.jenz.jsonparser.parser.JSONParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;

/**
 * Visualises the AST. From  https://stackoverflow.com/questions/49413911/antlr4-parse-tree-to-dot-using-dotgenerator .
 * Creates a dot file that can then be transformed into svg, png , .. using with graphvis, for instance:
 * dot -Tpng:cairo ast.dot -o ast.png
 * dot -Tsvg ast.dot -o ast.svg
 * The antlr tools (such as the intellij plugin can also visualise the AST but were unstable at the time of writting this.
 * @author jens dietrich
 */
public class QueryASTWithXPath {
    public static void main(String[] args) throws Exception {

        evaluateQuery("/input.json","//key");
        evaluateQuery("/input.json","//pair/value");
    }

    public static void evaluateQuery(String inputLocation,String query) throws Exception {
        URL url = JSON2XML.class.getResource(inputLocation);
        JSONLexer lexer = new JSONLexer(CharStreams.fromFileName(url.getFile()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        XPath p = new XPath(parser, query);
        Collection<ParseTree> results = p.evaluate(parser.json());

        int count = 0;
        for (ParseTree solution:results) {
            count = count+1;
            System.out.println("query result " + count + "/" + results.size()+" for query " + query + ":");
            String parseTreeAsString = solution.toStringTree(parser);
            System.out.println(parseTreeAsString);
            System.out.println();
        }
    }

}
