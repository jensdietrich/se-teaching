package nz.ac.vuw.jenz.visitor.iteration2;

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
	public void visit(ComplexTerm term) {}

	@Override
	public void visit(Condition condition) {}
	
	
	public Set<String> getVariables() {
		return variables;
	}
}