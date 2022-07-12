package nz.ac.vuw.jenz.javaparsing;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashCodeShouldNotReturnConstantTest {

    private Rule rule = new HashCodeShouldNotReturnConstantRule();

    @Test
    public void test1() throws FileNotFoundException {
        File src = new File(HashCodeShouldNotReturnConstantTest.class.getResource("/hashcode/Foo1.java").getFile());
        assertTrue(rule.detectViolation(src));
    }

    @Test
    public void test2() throws FileNotFoundException {
        File src = new File(HashCodeShouldNotReturnConstantTest.class.getResource("/hashcode/Foo2.java").getFile());
        assertFalse(rule.detectViolation(src));
    }

    @Test
    public void test3() throws FileNotFoundException {
        File src = new File(HashCodeShouldNotReturnConstantTest.class.getResource("/hashcode/Foo3.java").getFile());
        assertFalse(rule.detectViolation(src));
    }

}
