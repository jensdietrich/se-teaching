package nz.ac.vuw.jenz.codeql_examples.dataflow1;

public class Main {

    private static String FIELD = null;

    public static void main (String argM) throws Exception {
        FIELD = argM;
        foo();
    }

    private static void foo() throws Exception {
        bar(FIELD);
    }

    private static void bar(String argB) throws Exception {
        Runtime.getRuntime().exec(argB);
    }
}
