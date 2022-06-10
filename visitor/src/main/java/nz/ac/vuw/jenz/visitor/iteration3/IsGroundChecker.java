package nz.ac.vuw.jenz.visitor.iteration3;

/**
 * Example visitor, checks whether expressions are ground in the sense of prolog (have no variables).
 * Note that the unnecessary traversal of branches is avoided through the implementation of visit(ComplexTerm term).
 * @author jens dietrich
 */

class IsGroundChecker implements ExpressionVisitor {
	private boolean isGround = true;
	
	public IsGroundChecker() {
		super();
	}
	
	public boolean isGround() {
		return isGround;
	}

	@Override
	public void visit(Variable variable) {
		System.out.println("visiting leaf " + variable.getName());
		isGround = false;
	}

	@Override
	public void visit(Constant constant) {
		System.out.println("visiting leaf " + constant.getValue());
	}

	@Override
	public boolean visit(ComplexTerm term) {
		// if ground, keep on looking for variables
		return isGround;
	}

	@Override
	public boolean visit(Condition condition) {
		return true;
	}

	@Override
	public void endVisit(ComplexTerm term) {}

	@Override
	public void endVisit(Condition condition) {}
}