package nz.ac.vuw.jenz.visitor.iteration1;

public class Test1 {

	public static void main(String[] args) {
		
		// create object representing expression y = x+(z-42)
		Condition condition = new Condition(
			Operator.EQ,
			new Variable("y"),
			new ComplexTerm(
					Function.PLUS,
					new Variable("x"),
					new ComplexTerm(
							Function.MINUS,
							new Variable("z"),
							new Constant(42)
					)
			)
		);
		
		System.out.println(condition);

	}

}
