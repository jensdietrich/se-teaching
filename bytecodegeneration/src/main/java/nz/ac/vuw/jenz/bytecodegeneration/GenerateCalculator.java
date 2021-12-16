package nz.ac.vuw.jenz.bytecodegeneration;

import org.objectweb.asm.*;
import java.io.File;
import java.io.FileOutputStream;
import static org.objectweb.asm.Opcodes.*;

/**
 * Generate a simple calculator fclass (binary) using ASM (and bypassing javac), see Calculator.java for equivalent sources.
 * @author jens dietrich
 */
public class GenerateCalculator {
    public static void createClass (File classFile) throws Exception {
        ClassWriter classWriter = new ClassWriter(0);
        MethodVisitor methodVisitor;

        classWriter.visit(Opcodes.V1_8,
            Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
            "Calculator",   // the name
            null, //"LHelloWorld;", // no generic type parameters
            "java/lang/Object", // default super type
            null  // not interfaces implemented
            );
        //           classWriter.visitSource("Calculator.java", null);  // ???

        // default constructor
        //        methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        //        methodVisitor.visitCode();
        //        Label label0 = new Label();
        //        methodVisitor.visitLabel(label0);
        //        methodVisitor.visitLineNumber(5, label0);
        //        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        //        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        //        methodVisitor.visitInsn(Opcodes.RETURN);
        //        Label label1 = new Label();
        //        methodVisitor.visitLabel(label1);
        //        methodVisitor.visitLocalVariable("this", "LCalculator;", null, label0, label1, 0);
        //        methodVisitor.visitMaxs(1, 1);
        //        methodVisitor.visitEnd();


        methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLineNumber(6, label0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitInsn(AALOAD);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "parseInt", "(Ljava/lang/String;)I", false);
        methodVisitor.visitVarInsn(ISTORE, 1);

        Label label1 = new Label();
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLineNumber(7, label1);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitInsn(AALOAD);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "charAt", "(I)C", false);
        methodVisitor.visitVarInsn(ISTORE, 2);

        Label label2 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLineNumber(8, label2);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitInsn(ICONST_2);
        methodVisitor.visitInsn(AALOAD);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "parseInt", "(Ljava/lang/String;)I", false);
        methodVisitor.visitVarInsn(ISTORE, 3);

        Label label3 = new Label();
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLineNumber(10, label3);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitIntInsn(BIPUSH, 43);
        Label label4 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPNE, label4);
        Label label5 = new Label();
        methodVisitor.visitLabel(label5);
        methodVisitor.visitLineNumber(11, label5);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitVarInsn(ILOAD, 3);
        methodVisitor.visitInsn(IADD);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        Label label6 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label6);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLineNumber(13, label4);
        methodVisitor.visitFrame(Opcodes.F_APPEND,3, new Object[] {Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER}, 0, null);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitIntInsn(BIPUSH, 45);
        Label label7 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPNE, label7);
        Label label8 = new Label();
        methodVisitor.visitLabel(label8);
        methodVisitor.visitLineNumber(14, label8);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitVarInsn(ILOAD, 3);
        methodVisitor.visitInsn(ISUB);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        methodVisitor.visitJumpInsn(GOTO, label6);
        methodVisitor.visitLabel(label7);
        methodVisitor.visitLineNumber(17, label7);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("error");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor.visitLabel(label6);
        methodVisitor.visitLineNumber(19, label6);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitInsn(RETURN);
        Label label9 = new Label();
        methodVisitor.visitLabel(label9);
        methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, label0, label9, 0);
        methodVisitor.visitLocalVariable("i1", "I", null, label1, label9, 1);
        methodVisitor.visitLocalVariable("c", "C", null, label2, label9, 2);
        methodVisitor.visitLocalVariable("i2", "I", null, label3, label9, 3);
        methodVisitor.visitMaxs(3, 4);
        methodVisitor.visitEnd();

        classWriter.visitEnd();

        byte[] bytecode = classWriter.toByteArray();

        try (FileOutputStream fos = new FileOutputStream(classFile)) {
            fos.write(bytecode);
        }

        System.out.println("Byte code written to " + classFile.getAbsolutePath());

    }
}
