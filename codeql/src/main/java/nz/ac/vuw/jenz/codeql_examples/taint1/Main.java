package nz.ac.vuw.jenz.codeql_examples.taint1;

import java.net.MalformedURLException;
import java.net.URL;

// for https://codeql.github.com/docs/codeql-language-guides/analyzing-data-flow-in-java/#exercise-2
public class Main {
    public static void main (String arg) throws Exception {
        foo(arg);
    }
    private static void foo(String url) throws MalformedURLException {
        System.out.println(new URL(url));
    }
}
