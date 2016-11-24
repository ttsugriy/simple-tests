package concurrent;

import java.util.concurrent.Executor;

/**
 * Executors factory.
 */
public interface ExecutorsProvider {
    Executor newExecutor();
}
