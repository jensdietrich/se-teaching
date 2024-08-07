package nz.ac.vuw.jenz.asm.staticanalysis1.data2;

/**
 * Some sample class to be used in static analysis.
 * The compiler will generate a synthetic field in the inner class to reference an instance of the outer class.
 * NOTE: this behaviour has changed in Java 10
 * @author jens dietrich
 */
public class Outer {
    public void foo() {}
    class Inner {
        public void bar() {
            foo();}
    }
}
