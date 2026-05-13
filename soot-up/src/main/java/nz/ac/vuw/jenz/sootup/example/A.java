package nz.ac.vuw.jenz.sootup.example;

public class A {

    public static String start () {
        return foo("hey");
    }

    public static String foo (String arg) {
        System.out.println(arg);
        return arg.toUpperCase();
    }
}
