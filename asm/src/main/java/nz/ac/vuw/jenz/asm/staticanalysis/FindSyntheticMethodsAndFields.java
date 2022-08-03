package nz.ac.vuw.jenz.asm.staticanalysis;

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

        // collect classes
//        List<File> classFiles = new ArrayList<>();
//        Files.walkFileTree(input.toPath(), new SimpleFileVisitor<Path>() {
//            @Override
//            public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) throws IOException {
//                File file = p.toFile();
//                if (file.getName().endsWith(".class")) {
//                    classFiles.add(file);
//                }
//                return FileVisitResult.CONTINUE;
//            }
//        });
        List<File> classFiles = Files.walk(input.toPath())
            .map(p -> p.toFile())
            .filter(f -> f.getName().endsWith(".class"))
            .collect(Collectors.toList());

        // the actual analysis
        Set<String> syntheticMembers = new HashSet<>();
        for (File classFile : classFiles) {
            // System.out.println("Analysing: " + classFile);
            try (InputStream in = new FileInputStream(classFile)) {
                new ClassReader(in).accept(new SyntheticMemberFinder(syntheticMembers), 0);
            }
        }

        // do something with results -- print to console
        System.out.println("synthetic methods and fields found in " + input.getAbsolutePath());
        for (String member:syntheticMembers) {
            System.out.println(member);
        }
    }
}