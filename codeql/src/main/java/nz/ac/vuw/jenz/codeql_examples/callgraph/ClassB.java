package nz.ac.vuw.jenz.codeql_examples.callgraph;

public class ClassB {

    public void bar() {bar2();}

    public static void bar2() {}
}
