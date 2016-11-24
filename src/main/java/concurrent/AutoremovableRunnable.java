package concurrent;

/**
 * Runnable implementation which releases its monitor after it's executed.
 */
public class AutoremovableRunnable<T> implements Runnable {

    private final ExecutorManager<T> executorManager;
    private final MonitoredRunnable<T> runnable;

    public AutoremovableRunnable(ExecutorManager<T> executorManager, MonitoredRunnable<T> runnable) {
        this.executorManager = executorManager;
        this.runnable = runnable;
    }


    @Override
    public void run() {
        runnable.run();
        executorManager.freeExecutorForMonitor(runnable.getMonitor());
    }
}
