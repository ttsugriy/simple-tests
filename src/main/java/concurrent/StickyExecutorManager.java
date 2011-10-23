package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * User: goldberg333
 * Date: 23.10.11
 */
public class StickyExecutorManager<T> implements ExecutorManager<T> {

    private final ExecutorsProvider provider;
    private final int maxNumberOfSTE;
    private final Map<T, Executor> monitorToProvider;

    public StickyExecutorManager(ExecutorsProvider provider, int maxNumberOfSTE) {
        this.provider = provider;
        this.maxNumberOfSTE = maxNumberOfSTE;
        this.monitorToProvider = new HashMap<T, Executor>();
    }

    @Override
    public synchronized Executor getExecutorForMonitor(T monitor) {
        Executor executor = monitorToProvider.get(monitor);
        if (executor == null) {
            executor = provider.newExecutor();
        }
        return executor;
    }

    @Override
    public synchronized void freeExecutorForMonitor(T monitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
