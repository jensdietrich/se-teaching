package nz.ac.vuw.jenz.asm.staticanalysis3.data;

public class Foo {

    static void foo() {
        bar();
        bar(42);
        bar(1);
    }
    static void bar() {}

    static void bar(int i) {}
}
