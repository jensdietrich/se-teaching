package nz.ac.vuw.jenz.bytecode;

import java.util.stream.Stream;

public class StringPool {

    public static void main(String[] args) {
        char[] c = new char[]{'o','o','f'};

        String constructed = new String(c);
        // compare literal with (string constructed as) object
        System.out.println("comparing string defined as literals with string defined as object: " + "foo".equals(constructed));
    }
}
