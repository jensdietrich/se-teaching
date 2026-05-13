package nz.ac.vuw.jenz.sootup;

import sootup.callgraph.CallGraph;
import sootup.callgraph.CallGraphAlgorithm;
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.signatures.MethodSignature;
import sootup.core.typehierarchy.ViewTypeHierarchy;
import sootup.core.types.ClassType;
import sootup.java.bytecode.frontend.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaIdentifierFactory;
import sootup.java.core.JavaSootClass;
import sootup.java.core.JavaSootMethod;
import sootup.java.core.views.JavaView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Getting started with sootup.
 * @author jens dietrich
 */
public class Main {

    public static void main(String[] args) {
        List<AnalysisInputLocation> inputLocations = new ArrayList<>();
        inputLocations.add(new JavaClassPathAnalysisInputLocation("target/classes"));
        // inputLocations.add(new DefaultRTJarAnalysisInputLocation());

        JavaView view = new JavaView(inputLocations);

        ClassType classTypeA = view.getIdentifierFactory().getClassType("nz.ac.vuw.jenz.sootup.example.A");
        ClassType classTypeB = view.getIdentifierFactory().getClassType("B");
        MethodSignature entryMethodSignature = JavaIdentifierFactory.getInstance().getMethodSignature(classTypeA,"start","java.lang.String",List.of());

        assert entryMethodSignature != null;

        Optional<JavaSootClass> klass = view.getClass(classTypeA);
        assert klass.isPresent();

        Optional<JavaSootMethod> method = view.getMethod(entryMethodSignature);
        assert method.isPresent();

        // Create type hierarchy and CHA
        final ViewTypeHierarchy typeHierarchy = new ViewTypeHierarchy(view);
        System.out.println("subclass of "  + classTypeA + ": " + typeHierarchy.subclassesOf(classTypeA).toList());
        CallGraphAlgorithm cha = new ClassHierarchyAnalysisAlgorithm(view);

        // Create CG by initializing CHA with entry method(s)
        CallGraph cg = cha.initialize(Collections.singletonList(entryMethodSignature));


        cg.callsFrom(entryMethodSignature).forEach(
            e -> System.out.println(e)
        );
    }
}
