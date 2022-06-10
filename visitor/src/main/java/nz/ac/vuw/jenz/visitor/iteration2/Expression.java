package nz.ac.vuw.jenz.visitor.iteration2;

/**
 * Abstract visitable type, implemented by Term and Condition. 
 * @author jens dietrich
 */

public interface Expression {
	public void accept(ExpressionVisitor visitor);
}
