package test.nz.ac.vuw.jenz.pitest;

import static org.junit.Assert.*;
import nz.ac.vuw.jenz.pitest.TaxCalculator;
import org.junit.*;

/**
 * Tests cases for the income tax calculator based on the examples on the IRD web site:
 * http://www.ird.govt.nz/how-to/taxrates-codes/itaxsalaryandwage-incometaxrates.html
 * @author jens dietrich
 */
public class TaxCalculatorTest {

    private TaxCalculator calculator = null;

    @Before
    public void setup() {
        calculator = new TaxCalculator();
    }
    @After
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void test1() {
        double tax = calculator.calculateIncomeTax(65238);
        assertEquals(12591.40,tax,0.01);
    }

    @Test
    public void test2() {
        double tax = calculator.calculateIncomeTax(45000);
        assertEquals(6895.0,tax,0.01);
    }

    // boundary tests
    @Test
    public void testZero() {
        double tax = calculator.calculateIncomeTax(0);
        assertEquals(0,tax,0.01);
    }

    @Test
    public void testSmallIncome() {
        double tax = calculator.calculateIncomeTax(10);
        assertEquals(1.05,tax,0.01);
    }

    @Test
    public void testLargeIncome() {
        double tax = calculator.calculateIncomeTax(Integer.MAX_VALUE);
        // should be very close to the max tax rate
        assertEquals(0.33*Integer.MAX_VALUE,tax,Integer.MAX_VALUE*0.001);
    }

    @Test
    public void testNegativeIncome1() {
        try {
            @SuppressWarnings("unused")
            double tax = calculator.calculateIncomeTax(-42);
            assertTrue(false);
        }
        catch (IllegalArgumentException x) {
            assertTrue(true);
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeIncome2() {
        calculator.calculateIncomeTax(-42);
    }

    @Test(timeout=100)
    public void test1InclPerformance() {
        double tax = calculator.calculateIncomeTax(65238);
        assertEquals(12591.40,tax,0.01);
    }

    @Test(timeout=100)
    public void test2InclPerformance() {
        double tax = calculator.calculateIncomeTax(45000);
        assertEquals(6895.0,tax,0.01);
    }

}
