package nz.ac.vuw.jenz.staticnullpointeranalysis;

import org.checkerframework.checker.nullness.qual.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Checking class invariants with checkerframework.
 * @author jens dietrich
 */
public class InvariantExample1 {

    private @NonNull List fList = null;

    public static void main(String[] args) {
        boolean coinFlip = new Random().nextBoolean();
        List list = coinFlip ?  new ArrayList() : null;
        InvariantExample1 instance = new InvariantExample1();
        instance.fList = list;
    }

}
