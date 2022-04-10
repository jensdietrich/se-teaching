package nz.ac.vuw.jenz.coverage;

/**
 * Leap year calculator. Has some shortcomings, put in on purpose.
 * See https://en.wikipedia.org/wiki/Leap_year  , the 400 rule is missed.
 * @author jens dietrich
 */


public class LeapYearCalculator {
    public static boolean isLeapYear(int year) {
        if (year<0) throw new IllegalArgumentException("Years must be positive");
        return year%4==0 && year%100==0;
    }
}
