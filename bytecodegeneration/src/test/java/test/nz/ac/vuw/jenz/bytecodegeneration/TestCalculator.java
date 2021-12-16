package test.nz.ac.vuw.jenz.bytecodegeneration;

import nz.ac.vuw.jenz.bytecodegeneration.GenerateCalculator;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.lang.reflect.Method;

public class TestCalculator {

    @Test
    public void test() throws Exception {
        File folder = new File("generatedClasses");
        folder.mkdirs();
        File classFile = new File(folder,"Calculator.class");
        GenerateCalculator.createClass(classFile);

        // load generated class and test
        Class clazz = ClassLoadingGadget.loadClass(classFile,"Calculator");
        Method main = clazz.getMethod("main",String[].class);
        String[] mainArgs = new String[]{"2","+","3"};
        main.invoke(null,(Object)mainArgs);

        mainArgs = new String[]{"3","-","1"};
        main.invoke(null,(Object)mainArgs);
    }
}
