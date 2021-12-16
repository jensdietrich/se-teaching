package nz.ac.vuw.jenz.staticnullpointeranalysis;

import org.checkerframework.checker.nullness.qual.NonNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Checking postconditions with checkerframework.
 * The compiler will emmit a warning as createList may return null, but has a post-condition non-null.
 * @author jens dietrich
 */
public class PostConditionExample4 {

    public static void main(String[] args) {
        int size = createList(0).size();
        System.out.println("size is " + size);
    }

    @NonNull
    private static List createList(int kind) {
        switch (kind) {
            case 1: return new ArrayList();
            case 2: return new LinkedList();
            default: return new ArrayList();
        }
    }

}
