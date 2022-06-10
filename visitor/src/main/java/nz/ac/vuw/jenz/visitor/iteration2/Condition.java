package nz.ac.vuw.jenz.visitor.iteration2;

/**
 * Class to represent arithmetics conditions, such as x<y+2
 * @author jens dietrich
 */

public class Condition implements Expression {
	
	private Operator operator = null;
	private Term operand1 = null;
	private Term operand2 = null;
	
	public Condition(Operator operator, Term operand1, Term operand2) {
		super();
		this.operator = operator;
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	public Operator getOperator() {
		return operator;
	}
	public Term getOperand1() {
		return operand1;
	}
	public Term getOperand2() {
		return operand2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operand1 == null) ? 0 : operand1.hashCode());
		result = prime * result
				+ ((operand2 == null) ? 0 : operand2.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
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
		Condition other = (Condition) obj;
		if (operand1 == null) {
			if (other.operand1 != null)
				return false;
		} else if (!operand1.equals(other.operand1))
			return false;
		if (operand2 == null) {
			if (other.operand2 != null)
				return false;
		} else if (!operand2.equals(other.operand2))
			return false;
		if (operator != other.operator)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return new StringBuffer()
			.append(this.operand1)
			.append(this.operator)
			.append(this.operand2)
			.toString();
	}
	
	public void accept(ExpressionVisitor visitor) {
		this.operand1.accept(visitor);
		this.operand2.accept(visitor);
		visitor.visit(this);
	}
	

}
