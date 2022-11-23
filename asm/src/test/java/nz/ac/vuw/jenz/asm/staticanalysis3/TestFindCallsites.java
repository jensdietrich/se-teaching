package nz.ac.vuw.jenz.asm.staticanalysis3;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFindCallsites {

    @Test
    public void testCallsites() throws IOException {
        File classes = new File("target/test-classes/nz/ac/vuw/jenz/asm/staticanalysis3/data");
        Assumptions.assumeTrue(classes.exists());
        List<String> callsites = FindCallsites.findCallsites(classes);
        assertEquals(4,callsites.size());
        assertEquals(3l,callsites.stream().distinct().count()); // containts two callsites for same method
        assertTrue(callsites.contains("nz.ac.vuw.jenz.asm.staticanalysis3.data.Foo::foo()V -> nz/ac/vuw/jenz/asm/staticanalysis3/data/Foo::bar()V"));
        assertTrue(callsites.contains("nz.ac.vuw.jenz.asm.staticanalysis3.data.Foo::foo()V -> nz/ac/vuw/jenz/asm/staticanalysis3/data/Foo::bar(I)V"));

        // default constructor calls super() , i.e. new Object()
        assertTrue(callsites.contains("nz.ac.vuw.jenz.asm.staticanalysis3.data.Foo::<init>()V -> java/lang/Object::<init>()V"));
    }


}
