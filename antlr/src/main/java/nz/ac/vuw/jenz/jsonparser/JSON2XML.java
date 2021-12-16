package nz.ac.vuw.jenz.jsonparser;

import nz.ac.vuw.jenz.jsonparser.parser.JSONLexer;
import nz.ac.vuw.jenz.jsonparser.parser.JSONListener;
import nz.ac.vuw.jenz.jsonparser.parser.JSONParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;
import java.io.PrintStream;
import java.net.URL;

/**
 * Simple utility that transforms JSON to XML, illustrating how to work with the generated parser tree.
 * @author jens dietrich
 */
public class JSON2XML {

    private static int indentLevel = 0;
    public static void main(String[] args) throws Exception {

        URL url = JSON2XML.class.getResource("/input.json");
        JSONLexer lexer = new JSONLexer(CharStreams.fromFileName(url.getFile()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        ParseTree tree = parser.json();
        PrintStream out = System.out;
        JSONListener listener = new JSONListener() {
            @Override
            public void enterJson(JSONParser.JsonContext ctx) {
                out.println("<document>");
                indentLevel = indentLevel + 1;
            }
            @Override
            public void exitJson(JSONParser.JsonContext ctx) {
                indentLevel = indentLevel - 1;
                out.println("</document>");
            }
            @Override
            public void enterObj(JSONParser.ObjContext ctx) {
                printIndent(out);
                out.println("<object>");
                indentLevel = indentLevel + 1;
            }
            @Override
            public void exitObj(JSONParser.ObjContext ctx) {
                indentLevel = indentLevel - 1;
                printIndent(out);
                out.println("</object>");
            }
            @Override
            public void enterPair(JSONParser.PairContext ctx) {
                printIndent(out);
                out.println("<property>");
                indentLevel = indentLevel + 1;
            }
            @Override
            public void exitPair(JSONParser.PairContext ctx) {
                indentLevel = indentLevel - 1;
                printIndent(out);
                out.println("</property>");
            }
            @Override
            public void enterArr(JSONParser.ArrContext ctx) {
                printIndent(out);
                out.println("<array>");
                indentLevel = indentLevel + 1;
            }
            @Override
            public void exitArr(JSONParser.ArrContext ctx) {
                indentLevel = indentLevel - 1;
                printIndent(out);
                out.println("</array>");
            }
            @Override
            public void enterValue(JSONParser.ValueContext ctx) {
               if (ctx.STRING()!=null || ctx.NUMBER()!=null) {
                   printIndent(out);
                   out.println("<value>" + removeQuotes(ctx.getText()) + "</value>");
               }
            }
            @Override
            public void exitValue(JSONParser.ValueContext ctx) {
                if (ctx.STRING()!=null || ctx.NUMBER()!=null) {
                    //out.print(ctx.getText());
                }
            }
            @Override
            public void enterKey(JSONParser.KeyContext ctx) {
                printIndent(out);
                out.println("<name>" + removeQuotes(ctx.getText()) + "</name>");
            }
            @Override
            public void exitKey(JSONParser.KeyContext ctx) {

            }
            @Override
            public void visitTerminal(TerminalNode terminalNode) { }

            @Override
            public void visitErrorNode(ErrorNode errorNode) {
                System.out.println("Error " + errorNode.getText());
            }

            @Override
            public void enterEveryRule(ParserRuleContext parserRuleContext) { }

            @Override
            public void exitEveryRule(ParserRuleContext parserRuleContext) { }
        };

        ParseTreeWalker.DEFAULT.walk(listener, tree);
    }

    private static String removeQuotes(String text) {
        if (text.length()>1 && text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1,text.length()-1);
        }
        else if (text.length()>1 && text.startsWith("\'") && text.endsWith("\'")) {
            return text.substring(1,text.length()-1);
        }
        else {
            return text;
        }
    }

    private static void  printIndent(PrintStream out) {
        for (int i=0;i<indentLevel;i++) {
            out.print('\t');
        }
    }

}
