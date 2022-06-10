package nz.ac.vuw.jenz.visitor.iteration3;

/**
 * Class to represent arithmetics literals, such as 2 or 3.14
 * @author jens dietrich
 */

public class Constant extends Term {
	private double value = 0;

	public Constant(double value) {
		super();
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Constant other = (Constant) obj;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

	public double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
	
	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
}	
