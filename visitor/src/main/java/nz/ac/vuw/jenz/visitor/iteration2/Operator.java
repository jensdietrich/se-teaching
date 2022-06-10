package nz.ac.vuw.jenz.visitor.iteration2;

/**
 * Class to represent (binary) arithmetics operators, such as < and =
 * @author jens dietrich
 */

public enum Operator {
	LT,GT,EQ,NEQ,LTE,GTE;
	
	public String toString() {
		switch (this) {
			case EQ: return "=";
			case LT: return "<";
			case GT: return ">";
			case NEQ: return "!=";
			case LTE: return "<=";
			case GTE: return ">=";
		}
		return "?";
	}
}
