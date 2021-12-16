package test.nz.ac.vuw.jenz.bytecodegeneration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility to load a class from a generated .class file.
 * @author jens dietrich
 */
public class ClassLoadingGadget {
    static Class loadClass(File classFile, String className) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class findClass(String name) throws ClassNotFoundException {
                Path path = classFile.toPath();
                try {
                    byte[] data = Files.readAllBytes(path);
                    return defineClass(name, data, 0, data.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        return classLoader.loadClass(className);
    }
}
