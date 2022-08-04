package nz.ac.vuw.jenz.asm.instrumentation;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Instrument field writes and log the results on the console.
 * @author jens dietrich
 */
public class LogFieldWriteVisitor extends ClassVisitor {


    public LogFieldWriteVisitor(ClassWriter writer) {
        super(Opcodes.ASM9,writer);
    }
    private String className = null;

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature,exceptions);
        // constructors / static initialisers write fields -- just remove this to make functionality more transparent
        boolean isRegularMethod = !(name.equals("<clinit>") || name.equals("<init>"));
        if (isRegularMethod) {
            return new LogFieldAccessVisitor(mv);
        }
        else {
            return mv;
        }
    }

    static class LogFieldAccessVisitor extends MethodVisitor {
        private MethodVisitor mv = null;
        public LogFieldAccessVisitor(MethodVisitor mv) {
            super(Opcodes.ASM9, mv);
            this.mv = mv;
        }

        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
            if (opcode==Opcodes.PUTFIELD || opcode==Opcodes.PUTSTATIC) { // writes only
                // put arguments on stack for fieldAccessLogged invocation
                mv.visitLdcInsn(owner);
                mv.visitLdcInsn(name);
                // invoke #fieldAccessLogged
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, LogFieldWriteVisitor.class.getName().replace('.', '/'), "fieldAccessLogged", "(Ljava/lang/String;Ljava/lang/String;)V", false);
            }
            super.visitFieldInsn(opcode, owner, name, descriptor);
        }
    }

    public static void fieldAccessLogged(String clazz, String name) {
        String fieldInfo = clazz.replace('/','.') + "::" + name;
        AnalysisMemDB.add(fieldInfo);
    }

}
