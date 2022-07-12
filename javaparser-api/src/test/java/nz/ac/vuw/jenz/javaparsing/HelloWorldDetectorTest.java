package nz.ac.vuw.jenz.javaparsing;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloWorldDetectorTest {

    private Rule rule = new HelloWorldDetectorRule();
    @Test
    public void test1() throws FileNotFoundException {
        File src = new File(HelloWorldDetectorTest.class.getResource("/helloworld/Foo1.java").getFile());
        assertFalse(rule.detectViolation(src));
    }

    @Test
    public void test2() throws FileNotFoundException {
        File src = new File(HelloWorldDetectorTest.class.getResource("/helloworld/Foo2.java").getFile());
        assertFalse(rule.detectViolation(src));
    }

    @Test
    public void test3() throws FileNotFoundException {
        File src = new File(HelloWorldDetectorTest.class.getResource("/helloworld/Foo3.java").getFile());
        assertFalse(rule.detectViolation(src));
    }

    @Test
    public void test4() throws FileNotFoundException {
        File src = new File(HelloWorldDetectorTest.class.getResource("/helloworld/HelloWorld.java").getFile());
        assertTrue(rule.detectViolation(src));
    }

}
