package nz.ac.vuw.jenz.codeql_examples.subtyping;

public class ClassA implements Interface{
    @Override
    public void foo() {
        System.out.println("ClassA::foo");
    }
}
