package concurrent;

import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link AutoremovableRunnable}
 */
public class AutoremovableRunnableTest {

    @Test
    public void testThatAutoremovableRunnableRunsUnderlyingRunnableAndThanFreesMonitor() {
        ExecutorManager<String> executorManager = mock(ExecutorManager.class);
        MonitoredRunnable<String> runnable = mock(MonitoredRunnable.class);

        String monitor = "some monitor";
        when(runnable.getMonitor()).thenReturn(monitor);

        AutoremovableRunnable<String> autoremovableRunnable = new AutoremovableRunnable<String>(executorManager, runnable);

        autoremovableRunnable.run();

        InOrder inOrder = inOrder(executorManager, runnable);
        inOrder.verify(runnable).run();
        inOrder.verify(executorManager).freeExecutorForMonitor(monitor);
        
    }

}
