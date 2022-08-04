package nz.ac.vuw.jenz.asm.instrumentation.data;

public class Foo {

    public String f = "before";
    public static String F = "before";

    public void setf(String v) {
        this.f = v;
    }
    public static void setF(String v) {
        F = v;
    }
}
