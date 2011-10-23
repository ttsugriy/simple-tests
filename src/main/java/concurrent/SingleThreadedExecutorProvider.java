package concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * User: goldberg333
 * Date: 23.10.11
 */
public class SingleThreadedExecutorProvider implements ExecutorsProvider {

    @Override
    public Executor newExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
