package test.nz.ac.vuw.jenz.bdd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.ac.vuw.jenz.bdd.Adder;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

/**
 * Glue code that maps the natural language snippets used in features to junit constructs.
 */
public class Glue {

    private int computedValue;

    private Adder calculator;

    @Before
    private void init() {
        computedValue = Integer.MIN_VALUE;
    }

    @Given("^I have a calculator$")
    public void initializeCalculator() throws Throwable {
        calculator = new Adder();
    }

    @When("^I add (-?\\d+) and (-?\\d+)$")
    public void testAdd(int num1, int num2) throws Throwable {
        computedValue = calculator.plus(num1, num2);
    }

    @Then("^the result should be (-?\\d+)$")
    public void validateResult(int expectedValue) throws Throwable {
        assertEquals(expectedValue,computedValue);
    }
}
