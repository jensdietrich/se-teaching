package nz.ac.vuw.jenz.asm.staticanalysis1;

import nz.ac.vuw.jenz.asm.instrumentation.data.Foo;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFindSyntheticMethodsAndFields {

    @Test
    public void testSyntheticMethod() throws IOException {
        Foo obj = new Foo();
        File classes = new File("target/test-classes/nz/ac/vuw/jenz/asm/staticanalysis1/data1");
        Assumptions.assumeTrue(classes.exists());
        Set<String> syntheticMembers = FindSyntheticMethodsAndFields.findSyntheticMembers(classes);
        assertEquals(1,syntheticMembers.size());
        assertTrue(syntheticMembers.contains("nz.ac.vuw.jenz.asm.staticanalysis1.data1.B::foo()Ljava/lang/Object;"));
    }

    @Test
    public void testSyntheticField() throws IOException {
        Foo obj = new Foo();
        File classes = new File("target/test-classes/nz/ac/vuw/jenz/asm/staticanalysis1/data2");
        Assumptions.assumeTrue(classes.exists());
        Set<String> syntheticMembers = FindSyntheticMethodsAndFields.findSyntheticMembers(classes);
        assertEquals(1,syntheticMembers.size());
        assertTrue(syntheticMembers.contains("nz.ac.vuw.jenz.asm.staticanalysis1.data2.Outer$Inner::this$0"));
    }

}
