package nz.ac.vuw.jenz.bytecode.javac;

public class C {
    public static void main(String[] args) {
        // calls A::foo but will do so through a generated bridge method
        A obj = new B();
        obj.foo();
    }
}
