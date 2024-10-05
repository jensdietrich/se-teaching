package nz.ac.vuw.jenz.asm.cfg;

import com.google.common.base.Preconditions;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.analysis.ControlFlow;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Extracts the control flow graph of the methods in a class.
 * Works for loaded classes (i.e. the fully qualified name of a class in the classpath must be provided as argument),
 * this can be easily modified to use .class files as input,
 * see https://stackoverflow.com/questions/19066810/how-can-i-read-ctclass-from-class-file
 * @author jens dietrich
 */
public class BuildControlFlowGraph {


    public static void main(String[] args) throws Exception {
        Preconditions.checkArgument(args.length==1,"one argument required - the fully qualified name of a class");
        Class classUnderAnalysis = Class.forName(args[0]);
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(classUnderAnalysis.getName());

        for (CtMethod method:clazz.getMethods()) {
            Graph<String, DefaultEdge>  cfg = buildCFG(method);
            String fileName = method.getDeclaringClass().getName() + "::" + method.getName() + ".mermaid";
            export2Mermaid(cfg, Path.of(fileName));
        }
    }


    public static Graph<String, DefaultEdge> buildCFG(CtMethod method) throws IOException, BadBytecode {
        ControlFlow cf = new ControlFlow(method);
        ControlFlow.Block[] blocks = cf.basicBlocks();

        Graph<String, DefaultEdge> cfg = new DefaultDirectedGraph<>(DefaultEdge.class);
        Map<ControlFlow.Block,String> vertices = new HashMap<>();

        // vertices
        for (ControlFlow.Block block : blocks) {
            String vertex = toString(block);
            vertices.put(block, vertex);
            cfg.addVertex(vertex);
        }

        // edges
        for (ControlFlow.Block block : blocks) {
            String vertex = vertices.get(block);
            for (int exit=0;exit<block.exits();exit++) {
                String successor = vertices.get(block.exit(exit));
                assert successor!=null;
                cfg.addEdge(vertex, successor);
            }
        }
        System.out.println("control flow graph created");
        return cfg;
    }

    public static void export2Mermaid(Graph<String, DefaultEdge> cfg,Path file) throws IOException {
        String NL = System.lineSeparator();
        String mermaid = "graph TD;"+NL;
        for (DefaultEdge edge: cfg.edgeSet()) {
            mermaid = mermaid + "\t" + cfg.getEdgeSource(edge) + " --> " + cfg.getEdgeTarget(edge) + NL;
        }
        Files.write(file, mermaid.getBytes());
        System.out.println("graph exported in mermaid format to " + file.toString());
    }

    private static String toString(ControlFlow.Block block) {
        return "pos:"+block.position()+",len:"+block.length();
    }
}
