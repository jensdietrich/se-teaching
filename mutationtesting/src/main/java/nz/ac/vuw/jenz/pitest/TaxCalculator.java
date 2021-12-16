package nz.ac.vuw.jenz.pitest;
/**
 * A simple tax calculator implementing the NZ tax rules accourding to
 * http://www.ird.govt.nz/how-to/taxrates-codes/itaxsalaryandwage-incometaxrates.html
 * @author jens dietrich
 */
public class TaxCalculator {

    double[] brackets = {0,14000,48000,70000};
    double[] taxRates = {10.5,17.5,30,33};
    public double calculateIncomeTax(double income) {
        // disable the next line to introduce an artificial delay - this will cause test cases with timeouts to fail
        // try {Thread.sleep(200);} catch (Exception x){}
        if (income<0) throw new IllegalArgumentException("the income must be positive");
        double tax = 0.0;
        for (int i=brackets.length-1;i>=0;i--) {
            double bracket = brackets[i];
            double taxRate = taxRates[i];
            if (income>bracket) {
                double taxableInThisBracket = income-bracket;
                income = income - taxableInThisBracket;
                tax = tax + taxableInThisBracket*taxRate/100;
            }
        }
        return tax;
    }

}
