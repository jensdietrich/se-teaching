package nz.ac.vuw.jenz.mbean;

public class QueueMonitoring implements QueueMonitoringMBean {
    private java.util.Queue monitoredQueue = null;

    public QueueMonitoring(java.util.Queue monitoredQueue) {
        this.monitoredQueue = monitoredQueue;
    }

    public QueueMonitoring() {
    }

    public java.util.Queue getMonitoredQueue() {
        return monitoredQueue;
    }

    public void setMonitoredQueue(java.util.Queue monitoredQueue) {
        this.monitoredQueue = monitoredQueue;
    }

    @Override
    public int getSize() {
        return this.monitoredQueue.size();
    }
}
