package test.nz.ac.vuw.jenz.bytecodegeneration;

import nz.ac.vuw.jenz.bytecodegeneration.GenerateHelloWorld;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.lang.reflect.Method;

public class TestHelloWorld {

    @Test
    public void test() throws Exception {
        File folder = new File("generatedClasses");
        folder.mkdirs();
        File classFile = new File(folder,"HelloWord.class");
        GenerateHelloWorld.createClass(classFile);

        // load generated class and test
        Class clazz = ClassLoadingGadget.loadClass(classFile,"HelloWorld");
        Method main = clazz.getMethod("main",String[].class);
        String[] mainArgs = new String[]{};
        main.invoke(null,(Object)mainArgs);
    }
}
