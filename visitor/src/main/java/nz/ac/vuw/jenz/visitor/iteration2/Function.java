package nz.ac.vuw.jenz.visitor.iteration2;

/**
 * Class to represent (binary) arithmetics functions, such as + and x.
 * @author jens dietrich
 */

public enum Function {
	PLUS,MINUS,TIMES,DIV,MOD,POW;
	
	public String toString() {
		switch (this) {
			case PLUS: return "+";
			case MINUS: return "-";
			case TIMES: return "x";
			case DIV: return "/";
			case MOD: return "%";
			case POW: return "^";
		}
		return "?";
	}
	
}
