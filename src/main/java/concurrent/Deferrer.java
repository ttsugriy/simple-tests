package concurrent;

/**
 * A way to defer execution.
 */
public interface Deferrer {

    /**
     * Defers execution for specified amount of millis
     * @param millis millis to defer execution
     */
    void defer(long millis);
}
