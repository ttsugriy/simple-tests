package concurrent;

import java.util.concurrent.Executor;

/**
 * User: goldberg333
 * Date: 23.10.11
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
