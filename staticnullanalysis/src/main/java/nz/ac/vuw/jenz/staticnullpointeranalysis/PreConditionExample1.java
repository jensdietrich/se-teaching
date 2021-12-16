package nz.ac.vuw.jenz.staticnullpointeranalysis;

import org.checkerframework.checker.nullness.qual.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Checking preconditions with checkerframework.
 * The compiler will detect a possible nullpointer exception in main.
 * This is a true positive that actually would occur when executing the program.
 * @author jens dietrich
 */
public class PreConditionExample1 {

    public static void main(String[] args) {
        boolean coinFlip = new Random().nextBoolean();
        List list = coinFlip ?  new ArrayList() : null;
        printListSize(list);
    }

    private static void printListSize(@NonNull List list) {
        System.out.println("list size is " + list.size());
    }

}
