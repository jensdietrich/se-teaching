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
            if (leaves.size()==1) {
                JavaNode leaf = leaves.iterator().next();
                if (leaf instanceof ASTLiteral && ((ASTLiteral)leaf).isIntLiteral()) {
                    asCtx(data).addViolation(node);
                }
            }
        }
        return null;
    }

    private Set<JavaNode> collectLeaves(JavaNode node) {
        Set<JavaNode> leaves = new HashSet<>();
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
