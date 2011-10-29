package concurrent;

/**
 * User: goldberg333
 * Date: 23.10.11
 */
public abstract class MonitoredRunnable<T> implements Runnable {
    
    private final T monitor;

    protected MonitoredRunnable(T monitor) {
        this.monitor = monitor;
    }

    public T getMonitor() {
        return monitor;
    }
}
