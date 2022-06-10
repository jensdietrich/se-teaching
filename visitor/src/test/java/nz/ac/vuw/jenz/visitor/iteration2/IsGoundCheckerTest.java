package nz.ac.vuw.jenz.visitor.iteration2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsGoundCheckerTest {

	@Test
	public void test() {
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
		
		IsGroundChecker visitor = new IsGroundChecker();
		condition.accept(visitor);
		
		assertTrue(!visitor.isGround());
	}

}
