package concurrent;

import java.util.concurrent.Executor;

/**
 * Ensures sequential execution of runnables with same monitor by using
 * single threaded exeuctors.
 */
public class STEStickyExecutor<T> implements StickyExecutor<T> {
    private final StickyExecutorManager<T> executorManager;

    public STEStickyExecutor(StickyExecutorManager<T> executorManager) {
        this.executorManager = executorManager;
    }

    @Override
    public synchronized void execute(MonitoredRunnable<T> command) {
        T monitor = command.getMonitor();
        Executor executor = executorManager.getExecutorForMonitor(monitor);
        executor.execute(command);
    }

    @Override
    public void execute(Runnable command) {
        // TODO: implement
    }
}
