package nz.ac.vuw.jenz.visitor.iteration3;

/**
 * Visitor type. 
 * @author jens dietrich
 */

public interface ExpressionVisitor {
	// visit leafs
	void visit(Variable variable);
	void visit(Constant constant);
	
	// visit nodes with children
	boolean visit(ComplexTerm term);
	boolean visit(Condition condition);
	void endVisit(ComplexTerm term);
	void endVisit(Condition condition);

}
