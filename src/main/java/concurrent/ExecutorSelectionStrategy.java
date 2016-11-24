package concurrent;

import java.util.concurrent.Executor;

/**
 * Strategy for selecting executors.
 */
public interface ExecutorSelectionStrategy<T> {
    int selectExecutorNumberFor(T monitor);
}
