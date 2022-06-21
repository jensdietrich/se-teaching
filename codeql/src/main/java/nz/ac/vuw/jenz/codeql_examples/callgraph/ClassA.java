package nz.ac.vuw.jenz.codeql_examples.callgraph;

public class ClassA {

    public static void foo() {
        ClassB b = new ClassBa();
        b.bar();
    }
}
