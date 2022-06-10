package nz.ac.vuw.jenz.visitor.iteration2;

/**
 * Example visitor, checks whether expressions are ground in the sense of prolog (have no variables).
 * Note that all branches are visited, even after a variable has been found.
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
	public void visit(ComplexTerm term) {}

	@Override
	public void visit(Condition condition) {}

}