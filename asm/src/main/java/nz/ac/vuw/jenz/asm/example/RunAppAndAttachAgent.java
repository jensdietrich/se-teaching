package nz.ac.vuw.jenz.asm.example;

import com.sun.tools.attach.*;
import nz.ac.vuw.jenz.asm.instrumentation.LogFieldWriteVisitor;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Wrapper to App, and dynamically attach the agent (instead using the -javaagent option).
 *
 * Attach will only work if the JVM is started with this option: -Djdk.attach.allowAttachSelf=true
 * In an IDE , this option must be set in the run configuration.
 * @author jens dietrich
 */
public class RunAppAndAttachAgent {

    public static void main(String[] args) throws IOException {
        final RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        final long pid = runtime.getPid();
        VirtualMachine self = null;
        try {
            self = VirtualMachine.attach(""+pid);
        } catch (AttachNotSupportedException e) {
            System.out.println("Cannot attach to this VM, program must be started with JVM option \"-Djdk.attach.allowAttachSelf=true\"");
        }
        System.out.println("self attached to: " + self);

        File agentJar = new File("target/log-field-write-agent.jar");
        if (!agentJar.exists()) {
            throw new IllegalStateException("agent jar not found -- build project with \"mvn package\" first");
        }

        try {
            self.loadAgent(agentJar.getAbsolutePath());
        } catch (AgentLoadException| AgentInitializationException x) {
            System.err.println("error loading agent");
            x.printStackTrace();
        }

        // the actual invocation starts here =============================================================================
        App.main(new String[]{});
        // the actual invocation ends here =============================================================================

        // cleanup (optional)
        self.detach();
    }
}
