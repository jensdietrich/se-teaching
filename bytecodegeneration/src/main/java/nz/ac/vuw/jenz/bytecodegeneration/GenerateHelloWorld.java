package nz.ac.vuw.jenz.bytecodegeneration;

import org.objectweb.asm.*;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Generate a simple hello world class (binary) using ASM (and bypassing javac), see HelloWorld.java for equivalent sources.
 * @author jens dietrich
 */
public class GenerateHelloWorld {
    public static void createClass (File classFile) throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        MethodVisitor methodVisitor;

        classWriter.visit(Opcodes.V1_8,
                Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
                "HelloWorld",   // the name
                null, //"LHelloWorld;", // no generic type parameters
                "java/lang/Object", // default super type
                null  // not interfaces implemented
                );

        //           classWriter.visitSource("HelloWorld.java", null);  // ???

            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            methodVisitor.visitCode();
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(6, label3);
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("Hello World");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(7, label4);
            methodVisitor.visitInsn(Opcodes.RETURN);
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, label3, label5, 0);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();

            classWriter.visitEnd();

            byte[] bytecode = classWriter.toByteArray();

            try (FileOutputStream fos = new FileOutputStream(classFile)) {
                fos.write(bytecode);
            }

            System.out.println("Byte code written to " + classFile.getAbsolutePath());

    }
}
