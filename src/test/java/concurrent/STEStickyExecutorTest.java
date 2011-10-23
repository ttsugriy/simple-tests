/*
package concurrent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.concurrent.Executor;

import static org.mockito.Mockito.*;

*/
/**
 * User: goldberg333
 * Date: 23.10.11
 *//*

public class STEStickyExecutorTest {

    public static final int ONE_SINGLE_THREADED_EXECUTOR = 1;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testStickyExecutorCreatesAndUsesExecutorFromProvider() throws Exception {

        MonitoredRunnable<String> monitoredRunnable = mock(MonitoredRunnable.class);
        final String MONITOR = "some monitor";
        when(monitoredRunnable.getMonitor()).thenReturn(MONITOR);

        ExecutorsProvider provider = mock(ExecutorsProvider.class);

        Executor executor = mock(Executor.class);
        when(provider.newExecutor()).thenReturn(executor);

        StickyExecutor<String> stickyExecutor = new STEStickyExecutor<String>(provider, ONE_SINGLE_THREADED_EXECUTOR);
        stickyExecutor.execute(monitoredRunnable);
        stickyExecutor.execute(monitoredRunnable);
        stickyExecutor.execute(monitoredRunnable);

        int wantedNumberOfInvocations = 3;
        verify(provider).newExecutor();
        verify(executor, times(wantedNumberOfInvocations)).execute(monitoredRunnable);
    }

    @Test
    public void testStickyExecutorCreatesAndUsesExecutorFromProviderForTasksWithDifferentMonitors() throws Exception {

        MonitoredRunnable<String> monitoredRunnable1 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnable2 = mock(MonitoredRunnable.class);
        final String MONITOR1 = "some monitor";
        final String MONITOR2 = "some other monitor";
        when(monitoredRunnable1.getMonitor()).thenReturn(MONITOR1);
        when(monitoredRunnable2.getMonitor()).thenReturn(MONITOR2);

        ExecutorsProvider provider = mock(ExecutorsProvider.class);

        Executor executor = mock(Executor.class);
        when(provider.newExecutor()).thenReturn(executor);

        StickyExecutor<String> stickyExecutor = new STEStickyExecutor<String>(provider, ONE_SINGLE_THREADED_EXECUTOR);
        stickyExecutor.execute(monitoredRunnable1);
        stickyExecutor.execute(monitoredRunnable2);
        stickyExecutor.execute(monitoredRunnable1);

        int wantedNumberOfInvocationsForFirstTask = 2;
        int wantedNumberOfInvocationsForSecondTask = 1;
        verify(provider).newExecutor();
        verify(executor, times(wantedNumberOfInvocationsForFirstTask)).execute(monitoredRunnable1);
        verify(executor, times(wantedNumberOfInvocationsForSecondTask)).execute(monitoredRunnable2);
    }

*/
/*
    @Test
    public void testExecuteUsingSingleSingleThreadedExecutor() throws Exception {
        MonitoredRunnable<String> monitoredRunnableM1O1 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM1O2 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM1O3 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM2O1 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM2O2 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM2O3 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM3O1 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM3O2 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM3O3 = mock(MonitoredRunnable.class);

        ExecutorsProvider provider = mock(ExecutorsProvider.class);

        Executor executor = mock(Executor.class);

        when(provider.newExecutor()).thenReturn(executor);

        ExecutorSelectionStrategy<String> strategy = mock(ExecutorSelectionStrategy.class);

        StickyExecutor<String> stickyExecutor = new STEStickyExecutor<String>(provider, ONE_SINGLE_THREADED_EXECUTOR, strategy);
        stickyExecutor.execute(monitoredRunnableM1O1);
        stickyExecutor.execute(monitoredRunnableM2O1);
        stickyExecutor.execute(monitoredRunnableM3O1);
        stickyExecutor.execute(monitoredRunnableM1O2);
        stickyExecutor.execute(monitoredRunnableM2O2);
        stickyExecutor.execute(monitoredRunnableM3O2);
        stickyExecutor.execute(monitoredRunnableM3O3);
        stickyExecutor.execute(monitoredRunnableM2O3);
        stickyExecutor.execute(monitoredRunnableM1O3);

        ArgumentCaptor<MonitoredRunnable> executorCaptor = ArgumentCaptor.forClass(MonitoredRunnable.class);

        verify(executor).execute(executorCaptor.capture());

        verify(provider, atMost(ONE_SINGLE_THREADED_EXECUTOR)).newExecutor();
        InOrder inOrder1 = inOrder(executor);
        inOrder1.verify(executor).execute(monitoredRunnableM1O1);
        inOrder1.verify(executor).execute(monitoredRunnableM1O2);
        inOrder1.verify(executor).execute(monitoredRunnableM1O3);

        InOrder inOrder2 = inOrder(executor);
        inOrder2.verify(executor).execute(monitoredRunnableM2O1);
        inOrder2.verify(executor).execute(monitoredRunnableM2O2);
        inOrder2.verify(executor).execute(monitoredRunnableM2O3);

        InOrder inOrder3 = inOrder(executor);
        inOrder3.verify(executor).execute(monitoredRunnableM3O1);
        inOrder3.verify(executor).execute(monitoredRunnableM3O2);
        inOrder3.verify(executor).execute(monitoredRunnableM3O3);
    }
*//*


*/
/*    @Test
    public void testExecute() throws Exception {
        MonitoredRunnable<String> monitoredRunnableM1O1 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM1O2 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM1O3 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM2O1 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM2O2 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM2O3 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM3O1 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM3O2 = mock(MonitoredRunnable.class);
        MonitoredRunnable<String> monitoredRunnableM3O3 = mock(MonitoredRunnable.class);

        ExecutorsProvider provider = mock(ExecutorsProvider.class);

        Executor executor1 = mock(Executor.class);
        Executor executor2 = mock(Executor.class);
        Executor executor3 = mock(Executor.class);

        when(provider.newExecutor()).thenReturn(executor1, executor2, executor3);
        
        StickyExecutor<String> executor = new STEStickyExecutor<String>(provider, MAX_EXECUTORS_COUNT);
        executor.execute(monitoredRunnableM1O1);
        executor.execute(monitoredRunnableM2O1);
        executor.execute(monitoredRunnableM3O1);
        executor.execute(monitoredRunnableM1O2);
        executor.execute(monitoredRunnableM2O2);
        executor.execute(monitoredRunnableM3O2);
        executor.execute(monitoredRunnableM3O3);
        executor.execute(monitoredRunnableM2O3);
        executor.execute(monitoredRunnableM1O3);

        ArgumentCaptor<MonitoredRunnable<String>> executor1Captor = (ArgumentCaptor<MonitoredRunnable<String>>) ArgumentCaptor.forClass(MonitoredRunnable.class);
        ArgumentCaptor<MonitoredRunnable<String>> executor2Captor = (ArgumentCaptor<MonitoredRunnable<String>>) ArgumentCaptor.forClass(MonitoredRunnable.class);
        ArgumentCaptor<MonitoredRunnable<String>> executor3Captor = (ArgumentCaptor<MonitoredRunnable<String>>) ArgumentCaptor.forClass(MonitoredRunnable.class);

        executor1.execute(executor1Captor.capture());
        executor2.execute(executor2Captor.capture());
        executor3.execute(executor3Captor.capture());



        verify(provider, atMost(MAX_EXECUTORS_COUNT)).newExecutor();
    }*//*

}
*/
