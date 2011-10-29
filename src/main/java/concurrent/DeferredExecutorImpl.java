package concurrent;

import java.util.concurrent.Executor;

/**
 * User: goldberg333
 * Date: 22.10.11
 */
public class DeferredExecutorImpl implements DeferredExecutor {

    private final Executor executor;
    private final Deferrer deferrer;

    public DeferredExecutorImpl(Executor executor, Deferrer deferrer) {
        this.executor = executor;
        this.deferrer = deferrer;
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(command);
    }

    @Override
    public void execute(Runnable command, long delayInMillis) {
        executor.execute(new DeferredRunnable(deferrer, delayInMillis, command));
    }
}
