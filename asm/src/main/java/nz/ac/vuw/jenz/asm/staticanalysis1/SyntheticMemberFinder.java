package nz.ac.vuw.jenz.asm.staticanalysis1;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Set;

class SyntheticMemberFinder extends ClassVisitor {
    private Set<String> syntheticMembers = null;
    private String currentClass = null;

    public SyntheticMemberFinder(Set<String> syntheticMembers) {
        super(Opcodes.ASM9);  // bytecode version supported by asm
        this.syntheticMembers = syntheticMembers;
    }

    // visit class
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.currentClass = name.replace('/', '.');  // convert to source code syntax for better readability
    }

    // visit method
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if ((access & Opcodes.ACC_SYNTHETIC) != 0) {
            syntheticMembers.add(this.currentClass + "::" + name + descriptor);
        }
        return null; // do not traverse into
    }

    // visit field
    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if ((access & Opcodes.ACC_SYNTHETIC) != 0) {
            syntheticMembers.add(this.currentClass + "::" + name);
        }
        return null; // do not traverse into        }
    }
}
