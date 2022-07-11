package nz.ac.vuw.jenz.pmd;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.List;

/**
 * HelloWorld should not be used as a class name.
 * This is the HelloWorld of writing custom rules.
 * @author jens dietrich
 */
public class HelloWorldDetectorRule extends AbstractJavaRule {

    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        if (node.getSimpleName().contains("HelloWorld")) {
            System.out.println("Hello World has not been found");
            asCtx(data).addViolation(node);
        }
        // recurses into the branch !!
        return super.visit(node, data);
    }

}
