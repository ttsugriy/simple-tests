package concurrent;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: goldberg333
 * Date: 23.10.11
 */
public class StickyExecutorManagerTest {

    private Executor executor1;
    private Executor executor2;
    private Executor executor3;
    private ExecutorsProvider provider;

    @Before
    public void setUp() throws Exception {
        executor1 = mock(Executor.class);
        executor2 = mock(Executor.class);
        executor3 = mock(Executor.class);
        provider = mock(ExecutorsProvider.class);
        when(provider.newExecutor()).thenReturn(executor1, executor2, executor3);
    }

    @Test
    public void testGetExecutorForMonitorReturnsSameExecutorForSameNotProcessedMonitors() throws Exception {
        Executor executor = mock(Executor.class);

        ExecutorsProvider provider = mock(ExecutorsProvider.class);
        when(provider.newExecutor()).thenReturn(executor);

        int maxNumberOfSTE = 1;
        ExecutorManager<String> manager = new StickyExecutorManager<String>(provider, maxNumberOfSTE);

        assertThatAllExecutionsForSameMonitorResultInSameExecutor(manager, 1000);
        verify(provider).newExecutor();

    }

    private void assertThatAllExecutionsForSameMonitorResultInSameExecutor(ExecutorManager<String> manager, int numberOfGetters) {
        String monitor = "some monitor";

        Executor executorForMonitor = manager.getExecutorForMonitor(monitor);

        assertNotNull(executorForMonitor);
        for (int i = 0; i < numberOfGetters; ++i) {
            assertThat(executorForMonitor, sameInstance(manager.getExecutorForMonitor(monitor)));
        }
    }

    @Test
    public void testGetExecutorForMonitorReturnsTheOnlyExecutorAvailableForDifferentMonitors() throws Exception {
        Executor executor = mock(Executor.class);

        ExecutorsProvider provider = mock(ExecutorsProvider.class);
        when(provider.newExecutor()).thenReturn(executor);

        int maxNumberOfSTE = 1;
        ExecutorManager<String> manager = new StickyExecutorManager<String>(provider, maxNumberOfSTE);

        assertThatTwoInterleavingGettersReceiveSameExecutor(manager, 1000);
        verify(provider).newExecutor();

    }

    private void assertThatTwoInterleavingGettersReceiveSameExecutor(ExecutorManager<String> manager, int count) {
        String monitor1 = "some monitor1";
        String monitor2 = "some completely other monitor";

        Executor executorForMonitor = manager.getExecutorForMonitor(monitor1);
        assertNotNull(executorForMonitor);

        for (int i = 0; i < count; ++i) {
            assertThat(executorForMonitor, sameInstance(manager.getExecutorForMonitor(monitor1)));
            assertThat(executorForMonitor, sameInstance(manager.getExecutorForMonitor(monitor2)));
        }
    }

    @Test
    public void testGetExecutorForMonitorReturnsTheSameExecutorEvenWhenMultipleAreAvailableForTheSameMonitor() throws Exception {
        Executor executor = mock(Executor.class);

        ExecutorsProvider provider = mock(ExecutorsProvider.class);
        when(provider.newExecutor()).thenReturn(executor);

        int maxNumberOfSTE = 1000;
        ExecutorManager<String> manager = new StickyExecutorManager<String>(provider, maxNumberOfSTE);

        assertThatAllExecutionsForSameMonitorResultInSameExecutor(manager, 1000);
        verify(provider).newExecutor();

    }

    @Test
    public void testFreeExecutorForMonitor() throws Exception {
        int maxNumberOfSTE = 1;
        ExecutorManager<String> manager = new StickyExecutorManager<String>(provider, maxNumberOfSTE);

        String monitor = "some monitor";

        Executor executorForMonitor = manager.getExecutorForMonitor(monitor);

        assertNotNull(executorForMonitor);
        assertThat(executorForMonitor, sameInstance(manager.getExecutorForMonitor(monitor)));
        verify(provider).newExecutor();
    }
}
