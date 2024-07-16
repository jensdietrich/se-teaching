package nz.ac.vuw.jenz.mbean;

import org.apache.commons.lang3.RandomStringUtils;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Start the application. Note that the application will run forever, kill the process (in an IDE: push stop)
 * to stop it. The idea is to connect a JMX client like VisualVM to the application while it is running.
 * @author jens dietrich
 */
public class Main {

    private static int consumerCount = 2;
    private static int producerCount = 3;
    private static int queueMaxSize = 100;

    public static void main (String[] args) throws Exception {

        final BlockingQueue<String> queue = new ArrayBlockingQueue<>(queueMaxSize);

        for (int i=0;i<producerCount;i++) {
            String data = RandomStringUtils.random(100, true, true);
            Runnable producer = () -> {
                try {
                    while (true) {
                        Thread.sleep(10);
                        queue.put(data);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            new Thread(null,producer,"producer"+i,0).start();
        }

        for (int i=0;i<consumerCount;i++) {
            Runnable consumer = () -> {
                try {
                    while (true) {
                        queue.take();
                        // do something here
                        Thread.sleep(30); // simulate some processing ..
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            new Thread(null,consumer,"consumer"+i,0).start();
        }


        // register mbean
        ObjectName objectName = new ObjectName("nz.ac.vuw.jenz.mbean:type=basic,name=queue");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(new QueueMonitoring(queue), objectName);
    }
}
