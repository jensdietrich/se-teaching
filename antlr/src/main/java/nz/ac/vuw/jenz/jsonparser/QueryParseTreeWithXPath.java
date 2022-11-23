package nz.ac.vuw.jenz.jsonparser;

import nz.ac.vuw.jenz.jsonparser.parser.JSONLexer;
import nz.ac.vuw.jenz.jsonparser.parser.JSONParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;
import java.net.URL;
import java.util.Collection;

/**
 * Queries the parse tree / AST with XPath.
 * @author jens dietrich
 */
public class QueryParseTreeWithXPath {
    public static void main(String[] args) throws Exception {
        evaluateQuery("/input.json","//key");
        evaluateQuery("/input.json","//pair/value");
    }

    public static void evaluateQuery(String inputLocation,String query) throws Exception {
        URL url = QueryParseTreeWithXPath.class.getResource(inputLocation);
        JSONLexer lexer = new JSONLexer(CharStreams.fromFileName(url.getFile()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        XPath p = new XPath(parser, query);
        Collection<ParseTree> results = p.evaluate(parser.json());

        if (results.isEmpty()) {
            System.out.println("no results found for query " + query);
        }

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
