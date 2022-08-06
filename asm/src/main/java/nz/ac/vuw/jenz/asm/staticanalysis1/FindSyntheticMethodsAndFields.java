package nz.ac.vuw.jenz.asm.staticanalysis1;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Analysis to find compiler-generated methods and fields in bytecode.
 * @author jens dietrich
 */
public class FindSyntheticMethodsAndFields {

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
        Set<String> syntheticMembers = findSyntheticMembers(input);
        System.out.println("synthetic methods and fields found in " + input.getAbsolutePath());
        for (String member:syntheticMembers) {
            System.out.println(member);
        }
    }
    public static Set<String> findSyntheticMembers (File folderWithClassFiles) throws IOException {

        // collect class files
        List<File> classFiles = Files.walk(folderWithClassFiles.toPath())
            .map(p -> p.toFile())
            .filter(f -> f.getName().endsWith(".class"))
            .collect(Collectors.toList());

        // the actual analysis
        Set<String> syntheticMembers = new HashSet<>();
        for (File classFile : classFiles) {
            // System.out.println("Analysing: " + classFile);
            try (InputStream in = new FileInputStream(classFile)) {
                new ClassReader(in).accept(new SyntheticMemberVisitor(syntheticMembers), 0);
            }
        }
        return syntheticMembers;

    }
}