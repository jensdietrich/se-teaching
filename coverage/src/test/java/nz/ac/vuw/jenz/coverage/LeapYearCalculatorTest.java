package nz.ac.vuw.jenz.coverage;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void test5() {
        assertThrows(IllegalArgumentException.class, () -> LeapYearCalculator.isLeapYear(-42));
    }

}
