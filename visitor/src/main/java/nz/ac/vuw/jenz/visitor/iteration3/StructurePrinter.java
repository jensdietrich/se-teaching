package nz.ac.vuw.jenz.visitor.iteration3;

import java.io.PrintStream;
/**
 * Example visitor, prints the structure of the expression using indents and new lines chars. 
 * @author jens dietrich
 */

class StructurePrinter implements ExpressionVisitor {
	private int indent = 0;
	private PrintStream out = null;
	
	public StructurePrinter(PrintStream out) {
		super();
		this.out = out;
	}

	private void printIndent() {
		for (int i=0;i<indent;i++) {out.print("  ");}
	}

	@Override
	public void visit(Variable variable) {
		printIndent();
		out.println(variable.getName());
	}

	@Override
	public void visit(Constant constant) {
		printIndent();
		out.println(constant.getValue());
	}

	@Override
	public boolean visit(ComplexTerm term) {
		printIndent();
		out.println(term.getFunction());
		indent++;
		return true;
	}

	@Override
	public boolean visit(Condition condition) {
		printIndent();
		out.println(condition.getOperator());
		indent++;
		return true;
	}
	

	@Override
	public void endVisit(ComplexTerm term) {
		indent--;
	}

	@Override
	public void endVisit(Condition condition) {
		indent--;
	}
}