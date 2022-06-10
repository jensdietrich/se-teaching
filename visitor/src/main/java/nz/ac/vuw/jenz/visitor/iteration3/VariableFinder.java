package nz.ac.vuw.jenz.visitor.iteration3;

import java.util.*;

/**
 * Example visitor, extracts the variables used in a condition. 
 * @author jens dietrich
 */

class VariableFinder implements ExpressionVisitor {
	private Set<String> variables = new HashSet<String>();

	@Override
	public void visit(Variable variable) {
		this.variables.add(variable.getName());
	}

	@Override
	public void visit(Constant constant) {}

	@Override
	public boolean visit(ComplexTerm term) {
		return true;
	}

	@Override
	public boolean visit(Condition condition) {
		return true;
	}
	
	
	public Set<String> getVariables() {
		return variables;
	}

	@Override
	public void endVisit(ComplexTerm term) {}

	@Override
	public void endVisit(Condition condition) {}
}