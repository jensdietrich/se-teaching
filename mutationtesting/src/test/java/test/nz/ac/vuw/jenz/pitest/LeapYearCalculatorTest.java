package test.nz.ac.vuw.jenz.pitest;

import nz.ac.vuw.jenz.pitest.LeapYearCalculator;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * The tests are poor on purpose but provide excellent coverage.
 * @author jens dietrich
 */
public class LeapYearCalculatorTest {

    @Test
    public void test1() {
        LeapYearCalculator.isLeapYear(2018);
        assertTrue(true);
    }

    @Test
    public void test2() {
        LeapYearCalculator.isLeapYear(2019);
        assertTrue(true);
    }

    @Test
    public void test3() {
        LeapYearCalculator.isLeapYear(2020);
        assertTrue(true);
    }

    @Test
    public void test4() {
        LeapYearCalculator.isLeapYear(2000);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test5() {
        LeapYearCalculator.isLeapYear(-42);
    }

}
