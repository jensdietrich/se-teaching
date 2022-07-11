package nz.ac.vuw.jenz.pmd;

import net.sourceforge.pmd.lang.java.ast.*;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * hashcode should not return a constant as this would result deteriorated performance of hash-base containers.
 * @author jens dietrich
 */
public class HashcodeShouldNotReturnConstant extends AbstractJavaRule {

    private boolean isHashCode = false;


    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        isHashCode = node.getName().equals("hashCode") &&
                node.getFormalParameters().size()==0;

        // since overriding cannot change weaken visibility or change the return type (actually changing the return type
        // of a Java method to a subtype is possible (covariant return types), but this does not apply to int)
        // no further checks are needed to identify hashCode

        return super.visit(node, data);
    }

    @Override
    public Object visit(ASTReturnStatement node, Object data) {
        if (isHashCode) {
            Set<JavaNode> leaves = collectLeaves(node);
            Set<ASTLiteral> intLiteralLeaves = leaves.stream()
                .filter(ASTLiteral.class::isInstance)
                .map(ASTLiteral.class::cast)
                .filter(ASTLiteral::isIntLiteral)
                .collect(Collectors.toSet());
            if (leaves.size()==intLiteralLeaves.size()) {
                asCtx(data).addViolation(node);
            }
        }
        return null;
    }

    private Set<JavaNode> collectLeaves(JavaNode node) {

        Set<JavaNode> leaves = new HashSet<>();

        // the following block improves recall by correctly extracting return values from conditional expressions
        // to deal with code like "return flag?0:1"
        // the child node representing the condition is ignored
        if (node instanceof ASTConditionalExpression) {
            ASTConditionalExpression condExpr = (ASTConditionalExpression)node;
            leaves.addAll(collectLeaves(condExpr.getTrueAlternative()));
            leaves.addAll(collectLeaves((JavaNode)condExpr.getFalseAlternative()));
            return leaves;
        }

        if (node.getNumChildren()==0) {
            leaves.add(node);
        }
        else {
            for (JavaNode child:node.children()) {
                leaves.addAll(collectLeaves(child));
            }
        }
        return leaves;
    }

}
