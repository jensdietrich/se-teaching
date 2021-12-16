package nz.ac.vuw.jenz.bdd;

/**
 * Simple class to be tested.
 * There is an intentional error in the code in order to demonstrate how it is exposed by tests.
 */
public class Adder {
    public int plus (int n1,int n2) {
        return n1 * n2;
    }
}
