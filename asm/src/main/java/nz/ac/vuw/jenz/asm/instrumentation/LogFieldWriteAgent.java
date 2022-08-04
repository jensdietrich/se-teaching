package nz.ac.vuw.jenz.asm.instrumentation;

import nz.ac.vuw.jenz.asm.instrumentation.shaded.janala.instrument.SafeClassWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * ASM-based agent to log field writes.
 * @author jens dietrich
 */
public class LogFieldWriteAgent {

    public static final String INCLUDES = "nz/ac/";

    // agentmain needed for testing
    public static void agentmain(String agentArgs, Instrumentation inst) {

        premain(agentArgs,inst);
    }
    public static void premain(String agentArgs, Instrumentation inst) {

        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
            if (s.startsWith(INCLUDES)) {
                ClassReader reader = new ClassReader(bytes);
                // this prevents some issues with the standard class writer leading to duplicate class errors
                ClassWriter writer = new SafeClassWriter(reader,classLoader,ClassWriter.COMPUTE_FRAMES);
                try {
                    LogFieldWriteVisitor visitor = new LogFieldWriteVisitor(writer);
                    reader.accept(visitor, 0);
                    return writer.toByteArray();
                }
                catch (Throwable x) {
                    x.printStackTrace();
                }
            }
            return bytes;
            }
        });
    }


}
