package nz.ac.vuw.jenz.asm.staticanalysis2;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Set;

/**
 * Visitor to record field writes.
 * @author jens dietrich
 */
class FieldWriteVisitor extends ClassVisitor {
    private Set<String> fieldWrites = null;
    private String currentClass = null;
    private String currentMethod = null;

    public FieldWriteVisitor(Set<String> fieldWrites) {
        super(Opcodes.ASM9);  // bytecode version supported by asm
        this.fieldWrites = fieldWrites;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.currentClass = name.replace('/', '.');  // convert to source code syntax for better readability
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        this.currentMethod = this.currentClass + "::" + name ;  // skip descriptor to keep this simple
        return new MethodVisitor(Opcodes.ASM9) {
            @Override
            public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
                if (opcode==Opcodes.PUTFIELD || opcode==Opcodes.PUTSTATIC) {
                    fieldWrites.add(currentMethod + " -> " + name);
                }
            }
        };
    }

}
