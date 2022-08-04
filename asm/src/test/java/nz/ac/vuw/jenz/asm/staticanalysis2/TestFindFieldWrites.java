package nz.ac.vuw.jenz.asm.staticanalysis2;

import nz.ac.vuw.jenz.asm.instrumentation.data.Foo;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFindFieldWrites {

    @Test
    public void testFieldWrites() throws IOException {
        Foo obj = new Foo();
        File classes = new File("target/test-classes/nz/ac/vuw/jenz/asm/staticanalysis2/data");
        Assumptions.assumeTrue(classes.exists());
        Set<String> fieldWrites = FindFieldWrites.findFieldWrites(classes);
        assertEquals(4,fieldWrites.size());

        assertTrue(fieldWrites.contains("nz.ac.vuw.jenz.asm.staticanalysis2.data.Foo::m1 -> f"));
        assertTrue(fieldWrites.contains("nz.ac.vuw.jenz.asm.staticanalysis2.data.Foo::M1 -> F"));
        assertTrue(fieldWrites.contains("nz.ac.vuw.jenz.asm.staticanalysis2.data.Foo::<init> -> f"));
        assertTrue(fieldWrites.contains("nz.ac.vuw.jenz.asm.staticanalysis2.data.Foo::<clinit> -> F"));

    }


}
