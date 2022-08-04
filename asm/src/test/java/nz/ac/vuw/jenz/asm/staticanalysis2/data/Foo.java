package nz.ac.vuw.jenz.asm.staticanalysis2.data;

/**
 * Some sample class to be used in static analysis.
 * @author jens dietrich
 */
public class Foo {
    String f = null;
    static String F = null;

    void m1 () {
        this.f = ""; // field write
    }

    void m2 () {} // no field access

    String m3 () {
        return f;
    } // no field write

    static void M1 () {
        F = ""; // static field write
    }

    static void M2 () {} // no field access

    static String M3 () {
        return F;
    } // no field write
}
