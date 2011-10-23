package concurrent;

/**
 * User: goldberg333
 * Date: 23.10.11
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
