package concurrent;

import java.util.*;
import java.util.concurrent.Executor;

/**
 * User: goldberg333
 * Date: 23.10.11
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
