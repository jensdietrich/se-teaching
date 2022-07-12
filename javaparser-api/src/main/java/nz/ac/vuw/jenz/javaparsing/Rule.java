package nz.ac.vuw.jenz.javaparsing;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Very simple rule interface.
 * Violations are reported without further detail. This can be easily fixed by returning a more expressive result representation
 * that also has the location (line and column numbers) of the AST node where the issue was detected.
 * @author jens dietrich
 */
public interface Rule {
    boolean detectViolation (File javaSourceCode) throws FileNotFoundException;
}
