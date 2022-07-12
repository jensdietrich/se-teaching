package nz.ac.vuw.jenz.javaparsing;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple analysis to detect implementations of hashCode returning a constant.
 * @author jens dietrich
 */
public class HashCodeShouldNotReturnConstantRule implements Rule {

    // use functional style instead of visitor
    public boolean detectViolation (File javaSourceCode) throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(javaSourceCode);

        // simplified -- the nodes found also not the position in source code that can be reported ! 
        return cu.findAll(MethodDeclaration.class).stream()
            .filter(decl -> decl.getNameAsString().equals("hashCode"))
            .filter(decl -> decl.getParameters().size()==0)
            .flatMap(decl -> decl.findAll(ReturnStmt.class).stream())
            .anyMatch(returnStatement -> returnsConstant(returnStatement));
    }

    // simplified, ignores arithmetic expressions ("return 1+1;") and conditional
    // statements ("return f?0:1")
    private static boolean returnsConstant(ReturnStmt returnStatement) {
        List<Node> leaves = returnStatement.findAll(Node.class).stream()
            .filter(node -> node.getChildNodes().isEmpty())
            .collect(Collectors.toList());
        return leaves.size()==1 && leaves.get(0) instanceof IntegerLiteralExpr;
    }
}
