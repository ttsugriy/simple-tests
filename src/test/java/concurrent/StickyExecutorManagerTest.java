package concurrent;

import org.junit.Test;

import java.util.concurrent.Executor;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: goldberg333
 * Date: 23.10.11
 */
public class StickyExecutorManagerTest {
    @Test
    public void testGetExecutorForMonitorReturnsSameExecutorForSameNotProcessedMonitors() throws Exception {
        Executor executor = mock(Executor.class);

        ExecutorsProvider provider = mock(ExecutorsProvider.class);
        when(provider.newExecutor()).thenReturn(executor);

        int maxNumberOfSTE = 1;
        ExecutorManager<String> manager = new StickyExecutorManager<String>(provider, maxNumberOfSTE);

        String monitor = "some monitor";

        Executor executorForMonitor = manager.getExecutorForMonitor(monitor);
        assertNotNull(executorForMonitor);
        assertThat(executorForMonitor, sameInstance(manager.getExecutorForMonitor(monitor)));

    }

    @Test
    public void testFreeExecutorForMonitor() throws Exception {

    }
}
