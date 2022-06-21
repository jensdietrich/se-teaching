package nz.ac.vuw.jenz.codeql_examples.callgraph;

public class ClassBb extends ClassB {
    @Override
    public void bar() {
        super.bar();
        bar2();
    }
    public static void bar2() {}
}
