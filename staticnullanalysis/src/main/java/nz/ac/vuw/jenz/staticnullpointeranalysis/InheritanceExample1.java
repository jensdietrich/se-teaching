package nz.ac.vuw.jenz.staticnullpointeranalysis;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Checking inheritance / overriding rules with the checkerframework.
 * @author jens dietrich
 */
public class InheritanceExample1 {

    public static class A {
        // by default, assume that this cannot be null !
        public String foo() {
            return "foo";
        }
    }

    public static class B extends A {
        // violates LSP: postcondition is weakened as null might be returned
        // warning will be created
        public @Nullable String foo() {
            return null;
        }
    }

}
