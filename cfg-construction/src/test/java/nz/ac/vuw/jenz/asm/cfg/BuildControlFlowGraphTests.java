package nz.ac.vuw.jenz.asm.cfg;

import javassist.*;
import nz.ac.vuw.jenz.asm.cfg.testdata.Foo1;
import nz.ac.vuw.jenz.asm.cfg.testdata.Foo2;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildControlFlowGraphTests {


    @Test
    public void testFoo1() throws Exception {

        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(Foo1.class.getName());
        CtMethod method = clazz.getDeclaredMethod("foo");

        Graph<String, DefaultEdge> cfg = BuildControlFlowGraph.buildCFG(method);
        String fileName = method.getDeclaringClass().getName() + "::" + method.getName() + ".mermaid";
        BuildControlFlowGraph.export2Mermaid(cfg, Path.of(fileName));

        assertEquals(6,cfg.vertexSet().size());
        assertEquals(7,cfg.edgeSet().size());
    }

    @Test
    public void testFoo2() throws Exception {

        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(Foo2.class.getName());
        CtMethod method = clazz.getDeclaredMethod("foo");

        Graph<String, DefaultEdge> cfg = BuildControlFlowGraph.buildCFG(method);
        String fileName = method.getDeclaringClass().getName() + "::" + method.getName() + ".mermaid";
        BuildControlFlowGraph.export2Mermaid(cfg, Path.of(fileName));

        assertEquals(8,cfg.vertexSet().size());
        assertEquals(10,cfg.edgeSet().size());
    }
}
