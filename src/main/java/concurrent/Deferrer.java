package concurrent;

/**
 * User: goldberg333
 * Date: 22.10.11
 */
public interface Deferrer {

    /**
     * Defers execution for specified amount of millis
     * @param millis millis to defer execution
     */
    void defer(long millis);
}
