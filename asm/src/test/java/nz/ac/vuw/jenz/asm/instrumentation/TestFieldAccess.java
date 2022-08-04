package nz.ac.vuw.jenz.asm.instrumentation;

import com.ea.agentloader.AgentLoader;
import nz.ac.vuw.jenz.asm.instrumentation.data.*;
import org.junit.jupiter.api.*;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFieldAccess {

    @BeforeAll
    public static void installAgent() {
        AgentLoader.loadAgentClass(NullLoggerAgent.class.getName(),null);
    }

    @BeforeEach
    public void setup() {
        AnalysisMemDB.clear();
    }

    // disable this to inspect issue files
    @AfterEach
    public void tearDown() {
        AnalysisMemDB.clear();
    }

    @Test
    public void testNonStaticFieldWriteAccess() {
        Foo obj = new Foo();
        Assumptions.assumeTrue(Objects.equals(obj.f,"before"));
        Assumptions.assumeTrue(AnalysisMemDB.getFieldsWritten().isEmpty());

        obj.setf("after");
        assertEquals("after",obj.f);
        assertEquals(1, AnalysisMemDB.getFieldsWritten().size());
        assertTrue(AnalysisMemDB.getFieldsWritten().contains("nz.ac.vuw.jenz.asm.instrumentation.data.Foo::f"));

        for (String m:AnalysisMemDB.getFieldsWritten()) {
            System.out.println(m);
        }
    }

    @Test
    public void testStaticFieldWriteAccess() {
        Assumptions.assumeTrue(Objects.equals(Foo.F,"before"));
        Assumptions.assumeTrue(AnalysisMemDB.getFieldsWritten().isEmpty());

        Foo.setF("after");
        assertEquals("after",Foo.F);
        assertEquals(1, AnalysisMemDB.getFieldsWritten().size());
        assertTrue(AnalysisMemDB.getFieldsWritten().contains("nz.ac.vuw.jenz.asm.instrumentation.data.Foo::F"));

        for (String m:AnalysisMemDB.getFieldsWritten()) {
            System.out.println(m);
        }
    }

}
