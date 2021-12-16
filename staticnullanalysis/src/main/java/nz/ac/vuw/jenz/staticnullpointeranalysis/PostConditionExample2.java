package nz.ac.vuw.jenz.staticnullpointeranalysis;

import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Checking postconditions with checkerframework.
 * The compiler will detect a possible nullpointer exception in main.
 * This is a false positive that actually would occur when executing the program.
 * @author jens dietrich
 */
public class PostConditionExample2 {

    public static void main(String[] args) {
        int size = createList(1).size();
        System.out.println("size is " + size);
    }

    @Nullable
    private static List createList(int kind) {
        switch (kind) {
            case 1: return new ArrayList();
            case 2: return new LinkedList();
            default: return null;
        }
    }

}
