package nz.ac.vuw.jenz.visitor.iteration2;

/**
 * Class to represent complex terms, such as x+2 
 * @author jens dietrich
 */

public class ComplexTerm extends Term {
	
	private Function function = null;
	private Term childTerm1 = null;
	private Term childTerm2 = null;
	

	public ComplexTerm(Function function, Term childTerm1, Term childTerm2) {
		super();
		this.function = function;
		this.childTerm1 = childTerm1;
		this.childTerm2 = childTerm2;
	}
	public Function getFunction() {
		return function;
	}
	public Term getChildTerm1() {
		return childTerm1;
	}
	public Term getChildTerm2() {
		return childTerm2;
	}
	
	public Term[] getParts() {
		return new Term[]{childTerm1,childTerm2};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((childTerm1 == null) ? 0 : childTerm1.hashCode());
		result = prime * result
				+ ((childTerm2 == null) ? 0 : childTerm2.hashCode());
		result = prime * result
				+ ((function == null) ? 0 : function.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplexTerm other = (ComplexTerm) obj;
		if (childTerm1 == null) {
			if (other.childTerm1 != null)
				return false;
		} else if (!childTerm1.equals(other.childTerm1))
			return false;
		if (childTerm2 == null) {
			if (other.childTerm2 != null)
				return false;
		} else if (!childTerm2.equals(other.childTerm2))
			return false;
		if (function != other.function)
			return false;
		return true;
	}
	@Override
	public boolean isComplex() {
		return true;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append(this.childTerm1.isComplex()?"(":"")
			.append(this.childTerm1)
			.append(this.childTerm1.isComplex()?")":"")
			.append(this.function)
			.append(this.childTerm2.isComplex()?"(":"")
			.append(this.childTerm2)
			.append(this.childTerm2.isComplex()?")":"")
			.toString();
	}
	
	@Override
	public void accept(ExpressionVisitor visitor) {
		this.childTerm1.accept(visitor);
		this.childTerm2.accept(visitor);
		visitor.visit(this);
	}
	
}
