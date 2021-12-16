package nz.ac.vuw.jenz.bytecodegeneration;

/**
 * Calculator reference source code.
 * @author jens dietrich
 */
public class Calculator {

    public static void main(String[] args) {
        int i1 = Integer.parseInt(args[0]);
        char c = args[1].charAt(0);
        int i2 = Integer.parseInt(args[2]);

        if (c=='+') {
            System.out.println(i1+i2);
        }
        else if (c=='-') {
            System.out.println(i1-i2);
        }
        else {
            System.out.println("error");
        }
    }
}
