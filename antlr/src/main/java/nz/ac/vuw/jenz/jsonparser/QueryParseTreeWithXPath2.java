package nz.ac.vuw.jenz.jsonparser;

/**
 * Queries the parse tree / AST with XPath. Same as QueryParseTreeWithXPath, just using different data
 * and counts results. An example for special occasions :-)
 * @author jens dietrich
 */
public class QueryParseTreeWithXPath2 {
    public static void main(String[] args) throws Exception {
        QueryParseTreeWithXPath.evaluateQuery("/input2.json","//pair/value");
        System.out.println("-------------");
        QueryParseTreeWithXPath.evaluateQuery("/input2.json","//value");
        System.out.println("-------------");
        QueryParseTreeWithXPath.evaluateQuery("/input2.json","/key");
        System.out.println("-------------");
        QueryParseTreeWithXPath.evaluateQuery("/input2.json","//key");
    }
}