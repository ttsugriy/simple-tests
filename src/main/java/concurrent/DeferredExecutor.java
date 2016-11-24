package concurrent;

import java.util.concurrent.Executor;

/**
 * Executor which allows to defer runnable execution.
 */
public interface DeferredExecutor extends Executor {
    public void execute(Runnable command, long delayInMillis);
}
