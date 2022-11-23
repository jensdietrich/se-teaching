package nz.ac.vuw.jenz.asm.staticanalysis3;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;
import java.util.Set;

/**
 * Visitor to record field writes.
 * @author jens dietrich
 */
class RecordCallsitesVisitor extends ClassVisitor {
    private List<String> callsites = null;
    private String currentClass = null;
    private String currentMethod = null;

    public RecordCallsitesVisitor(List<String> callsites) {
        super(Opcodes.ASM9);  // bytecode version supported by asm
        this.callsites = callsites;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.currentClass = name.replace('/', '.');  // convert to source code syntax for better readability
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        this.currentMethod = this.currentClass + "::" + name + descriptor;
        return new MethodVisitor(Opcodes.ASM9) {
            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                String callee = owner + "::" + name + descriptor;
                String callsite = currentMethod + " -> " + callee;
                callsites.add(callsite);
            }


        };
    }

}
