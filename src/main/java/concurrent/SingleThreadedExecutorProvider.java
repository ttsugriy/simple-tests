package concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Provides instances of single threaded executors.
 */
public class SingleThreadedExecutorProvider implements ExecutorsProvider {

    @Override
    public Executor newExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
