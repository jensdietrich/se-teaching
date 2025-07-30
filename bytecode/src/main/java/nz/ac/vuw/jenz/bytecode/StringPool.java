package nz.ac.vuw.jenz.bytecode;

public class StringPool {

    public static void main(String[] args) {
        char[] c = new char[]{'f','o','o'};
        String constructed = new String(c);
        // compare literal with (string constructed as) object
        System.out.println("identical: " + ("foo"==constructed));
        System.out.println("equal: " + "foo".equals(constructed));
        System.out.println("indentical to internal: " + ("foo"==constructed.intern()));
    }
}
