package concurrent;

import org.junit.Test;

import java.util.concurrent.Executor;

import static org.mockito.Mockito.*;

/**
 * Tests for {@link STEStickyExecutor}
 */
public class STEStickyExecutorTest {

    @Test
    public void testStickyExecutorUsesExecutorFromProvidedExecutorManager() throws Exception {

        MonitoredRunnable<String> monitoredRunnable = mock(MonitoredRunnable.class);
        final String MONITOR = "some monitor";
        when(monitoredRunnable.getMonitor()).thenReturn(MONITOR);

        StickyExecutorManager<String> stickyExecutorManager = mock(StickyExecutorManager.class);
        Executor executor = mock(Executor.class);
        when(stickyExecutorManager.getExecutorForMonitor(MONITOR)).thenReturn(executor);

        StickyExecutor<String> stickyExecutor = new STEStickyExecutor<String>(stickyExecutorManager);
        stickyExecutor.execute(monitoredRunnable);
        
        verify(stickyExecutorManager).getExecutorForMonitor(MONITOR);
        verify(executor).execute(monitoredRunnable);

    }

}
