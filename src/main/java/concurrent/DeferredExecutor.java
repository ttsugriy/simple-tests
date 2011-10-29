package concurrent;

import java.util.concurrent.Executor;

/**
 * User: goldberg333
 * Date: 22.10.11
 */
public interface DeferredExecutor extends Executor {
    public void execute(Runnable command, long delayInMillis);
}
