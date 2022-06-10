package nz.ac.vuw.jenz.visitor.iteration3;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VariableFinderTest {

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
		
		VariableFinder visitor = new VariableFinder();
		condition.accept(visitor);
		
		assertEquals(3,visitor.getVariables().size());
		assertTrue(visitor.getVariables().contains("x"));
		assertTrue(visitor.getVariables().contains("y"));
		assertTrue(visitor.getVariables().contains("z"));
	}

}
