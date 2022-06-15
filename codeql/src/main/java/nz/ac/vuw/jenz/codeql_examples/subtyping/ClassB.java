package nz.ac.vuw.jenz.codeql_examples.subtyping;

public class ClassB extends ClassA {
    @Override
    public void foo() {
        System.out.println("ClassB::foo");
    }
}
