package nz.ac.vuw.jenz.asm.staticanalysis2;

import org.objectweb.asm.ClassReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Analysis to find field writes.
 * @author jens dietrich
 */
public class FindFieldWrites {

    public static void main(String[] args) throws IOException {
        // some precondition checks
        if (args.length == 0) {
            throw new IllegalArgumentException("one argument required -- the folder containing .class files to be analysed (hint: build project, and then use target/classes to analyse itself)");
        }
        File input = new File(args[0]);
        if (!input.exists() || !input.isDirectory()) {
            throw new IllegalArgumentException("input folder does not exist: " + input.getAbsolutePath());
        }

        // do something with results -- print to console
        Set<String> fieldWrites = findFieldWrites(input);
        System.out.println("methods with field writes found in " + input.getAbsolutePath());
        for (String fieldWrite:fieldWrites) {
            System.out.println(fieldWrite);
        }
    }
    public static Set<String> findFieldWrites(File folderWithClassFiles) throws IOException {

        // collect class files
        List<File> classFiles = Files.walk(folderWithClassFiles.toPath())
            .map(p -> p.toFile())
            .filter(f -> f.getName().endsWith(".class"))
            .collect(Collectors.toList());

        // the actual analysis
        Set<String> fieldWrites = new HashSet<>();
        for (File classFile : classFiles) {
            // System.out.println("Analysing: " + classFile);
            try (InputStream in = new FileInputStream(classFile)) {
                new ClassReader(in).accept(new FieldWriteVisitor(fieldWrites), 0);
            }
        }
        return fieldWrites;

    }
}