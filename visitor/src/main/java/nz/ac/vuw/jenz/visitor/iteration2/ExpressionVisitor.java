package nz.ac.vuw.jenz.visitor.iteration2;

/**
 * Visitor type. 
 * @author jens dietrich
 */

public interface ExpressionVisitor {
	
	void visit(Variable variable);
	void visit(Constant constant);
	void visit(ComplexTerm term);
	void visit(Condition condition);

}
