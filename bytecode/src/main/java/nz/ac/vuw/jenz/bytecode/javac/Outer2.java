package nz.ac.vuw.jenz.bytecode.javac;

public class Outer2 {
    void foo() {}
    class Inner {
        void bar() {
            foo();
        }
    }
}
