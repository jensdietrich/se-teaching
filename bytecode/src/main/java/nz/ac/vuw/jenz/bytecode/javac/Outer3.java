package nz.ac.vuw.jenz.bytecode.javac;

public class Outer3 {
    private void foo() {}
    class Inner {
        void bar() {
            foo();
        }
    }
}
