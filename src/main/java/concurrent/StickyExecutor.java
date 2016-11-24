package concurrent;

import java.util.concurrent.Executor;

/**
 * Executor which uses monitors to make sure that runnables
 * with the same monitor are executed sequentially.
 */
public interface StickyExecutor<T> extends Executor {
    public void execute(MonitoredRunnable<T> command);
}
