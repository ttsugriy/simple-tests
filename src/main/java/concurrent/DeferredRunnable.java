package concurrent;

/**
 * Runnable implementation which knows how to defer its execution.
 */
public class DeferredRunnable implements Runnable {
    private final Deferrer deferrer;
    private final long delayInMillis;
    private final Runnable runnable;

    public DeferredRunnable(Deferrer deferrer, long delayInMillis, Runnable runnable) {
        this.deferrer = deferrer;
        this.delayInMillis = delayInMillis;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        deferrer.defer(delayInMillis);
        runnable.run();
    }

    public long getDelayInMillis() {
        return delayInMillis;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public Deferrer getDeferrer() {
        return deferrer;
    }
}
