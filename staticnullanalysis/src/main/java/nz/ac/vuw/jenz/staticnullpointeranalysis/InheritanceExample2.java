package nz.ac.vuw.jenz.staticnullpointeranalysis;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Checking inheritance / overriding rules with the checkerframework.
 * @author jens dietrich
 */
public class InheritanceExample2 {

    public static class A {
        public @Nullable  String foo() {
            return null;
        }
    }

    // this is ok, the postcondition is actually strengthened !
    public static class B extends A {
        public String foo() {
            return "foo";
        }
    }

}
