package concurrent;

/**
 * Runnable with a coordination monitor.
 *
 * Minotors can be unique or identifying groups of runnables.
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
