package nz.ac.vuw.jenz.bytecode;

public class StaticInvocation {
    public static String foo(int i) {
        return "foo";
    }
    public static String bar() {
        return foo(42);
    }
}
