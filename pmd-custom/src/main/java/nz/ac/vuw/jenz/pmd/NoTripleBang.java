package nz.ac.vuw.jenz.pmd;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTUnaryExpression;
import net.sourceforge.pmd.lang.ecmascript.rule.AbstractEcmascriptRule;

/**
 * This is a JavaScript rule !
 * In JavaScript, the double bang (exclamation mark) (!!) is often used for casting. However, using the triple bang
 * (!!!) does not make sense, and it can be replaced by a single bang.
 * @author jens dietrich
 */
public class NoTripleBang extends AbstractEcmascriptRule {
    @Override
    public Object visit(ASTUnaryExpression node, Object data) {
        assert node.getParent()!=null;
        if (isBangOp(node) && isBangOp(node.getParent()) && isBangOp(node.getParent().getParent())) {
            asCtx(data).addViolation(node);
        }
        return super.visit(node, data);
    }

    private boolean isBangOp(Node node) {
        return node!=null
            && node instanceof ASTUnaryExpression
            && node.getImage().equals("!");
    }
}
