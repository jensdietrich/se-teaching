package nz.ac.vuw.jenz.asm.staticanalysis1.data1;

/**
 * Some sample class to be used in static analysis.
 * Uses co-variant return types, the static analysis FindSyntheticMethodsAndFields
 * will therefore pick up a synthetic bridge method.
 * @author jens dietrich
 */
public class B extends A {
    @Override String foo() {return "";}
}
