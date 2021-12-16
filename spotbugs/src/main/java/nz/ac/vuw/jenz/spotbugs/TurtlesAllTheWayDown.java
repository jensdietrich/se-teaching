package nz.ac.vuw.jenz.spotbugs;

/**
 * Class containing several semantic bug including a recursive and a non-recursive loop,
 * and an unused constant.
 * @author jens dietrich
 */
public class TurtlesAllTheWayDown {

    public String someField = null;

    public static void main(String[] args) {
        doSomethingLoopy();
        System.out.println("This is fun, lets try this again");
        main(args);
    }

    public static void doSomethingLoopy() {
        int i = 0;
        while (i<42) {
            System.out.println("I feel loopy ");
        }
    }
}
