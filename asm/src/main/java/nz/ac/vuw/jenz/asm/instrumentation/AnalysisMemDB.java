package nz.ac.vuw.jenz.asm.instrumentation;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple data structure to record fields written.
 * The agent collects information there. Could be accessed to write to file upon JVM termination, access via JMX, ..
 * @author jens dietrich
 */
public class AnalysisMemDB {
    private final static Set<String> fieldsWritten = new HashSet<>();

    public static Set<String> getFieldsWritten() {
        return fieldsWritten;
    }

    public static void add(String fieldInfo) {
        if (fieldsWritten.add(fieldInfo)) {
            System.out.println("field access recorded: " + fieldInfo);
        }
    }

    // for testing
    static void clear() {
        fieldsWritten.clear();
    }
}
