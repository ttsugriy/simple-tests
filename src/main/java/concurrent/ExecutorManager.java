package concurrent;

import java.util.concurrent.Executor;

/**
 * User: goldberg333
 * Date: 23.10.11
 */
public interface ExecutorManager<T> {

    /**
     * Get executor for provided monitor
     * @param monitor monitor
     * @return executor for provided monitor
     */
    Executor getExecutorForMonitor(T monitor);

    /**
     * Free executor for provided monitor. If it's the only connection of monitor and executor
     * this connection will be destroyed. Otherwise connection will stay.
     * @param monitor monitor
     */
    void freeExecutorForMonitor(T monitor);
    
}
