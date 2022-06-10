package nz.ac.vuw.jenz.visitor.iteration3;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StructurePrinterTest {

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
		
		StructurePrinter visitor = new StructurePrinter(System.out);
		condition.accept(visitor);
		
		assertTrue(true);
	}

}
