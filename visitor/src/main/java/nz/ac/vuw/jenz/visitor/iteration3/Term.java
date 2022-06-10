package nz.ac.vuw.jenz.visitor.iteration3;

/**
 * Abstract class to represent arithmetics terms, see subclasses for details.
 * @author jens dietrich
 */
public abstract class Term implements Expression {
	public boolean isComplex() {
		return false;
	}
}
