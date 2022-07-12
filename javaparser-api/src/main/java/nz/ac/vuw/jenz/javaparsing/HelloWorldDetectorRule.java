package nz.ac.vuw.jenz.javaparsing;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Simple analysis to detect whether a class has the name HelloWorld.
 * @author jens dietrich
 */
public class HelloWorldDetectorRule implements Rule {

    // use functional style instead of visitor
    public boolean detectViolation (File javaSourceCode) throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(javaSourceCode);

        // simplified -- the nodes found also not the position in source code that can be reported !
        return cu.findAll(ClassOrInterfaceDeclaration.class).stream()
            .anyMatch(decl -> decl.getNameAsString().equals("HelloWorld"));
    }
}
