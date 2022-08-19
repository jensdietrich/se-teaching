package nz.ac.vuw.jenz.bytecode.javac;

public class Outer3 {

    // note that the compilation strategy depends on the version of Java being used
    // older versions of the compiler (before 11) create special access methods to access private members
    // in outer classes
    // see https://openjdk.org/jeps/181  for details of changes in Java 11 !
    private void foo() {}
    class Inner {
        void bar() {
            foo();
        }
    }
}
