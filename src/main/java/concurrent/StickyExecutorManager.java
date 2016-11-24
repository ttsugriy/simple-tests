package concurrent;

import java.util.*;
import java.util.concurrent.Executor;

/**
 * Executor manager implementation which uses monitors to execute runnables
 * with same monitor sequentially.
 *
 * Submitted runnables with different monitors may or may not run in parallel,
 * subject to executor service availability.
 */
public class StickyExecutorManager<T> implements ExecutorManager<T> {
    private final ExecutorsProvider provider;
    private final int maxNumberOfSTE;
    private final Map<T, Executor> monitorToProvider;
    private final List<Executor> executors;

    public StickyExecutorManager(ExecutorsProvider provider, int maxNumberOfSTE) {
        this.provider = provider;
        this.maxNumberOfSTE = maxNumberOfSTE;
        this.monitorToProvider = new HashMap<T, Executor>();
        this.executors = new ArrayList<Executor>(maxNumberOfSTE);
    }

    @Override
    public synchronized Executor getExecutorForMonitor(T monitor) {
        Executor executor = monitorToProvider.get(monitor);
        if (executor == null) {
            if (thereAreStillAvailableExecutorSlots()) {
                executor = provider.newExecutor();
                executors.add(executor);
            } else {
                executor = executors.get(newExecutorPositionFor(monitor));
            }
            monitorToProvider.put(monitor, executor);
        }
        return executor;
    }

    private int newExecutorPositionFor(T monitor) {
        return monitor.hashCode() % maxNumberOfSTE;
    }

    private boolean thereAreStillAvailableExecutorSlots() {
        return executors.size() < maxNumberOfSTE;
    }

    @Override
    public synchronized void freeExecutorForMonitor(T monitor) {
       // TODO: implement
    }
}
