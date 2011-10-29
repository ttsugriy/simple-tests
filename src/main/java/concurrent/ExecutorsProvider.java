package concurrent;

import java.util.concurrent.Executor;

/**
 * User: goldberg333
 * Date: 23.10.11
 */
public interface ExecutorsProvider {
    Executor newExecutor();
}
