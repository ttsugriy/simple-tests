package concurrent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link DeferredExecutorImpl}
 */
public class DeferredExecutorImplTest {

    @Mock
    private Executor executor;
    @Mock
    private Deferrer deferrer;
    @Mock
    private DeferredRunnable command;

    private DeferredExecutor deferredExecutor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        deferredExecutor = new DeferredExecutorImpl(executor, deferrer);
    }

    @Test
    public void testDeferredExecutorBehavesAsSimpleExecutorWhenNoDelayProvided() {
        deferredExecutor.execute(command);
        verify(executor).execute(command);
    }

    @Test
    public void testDeferredExecutorDefersExecution() {
        int delayInMillis = 3000;
        deferredExecutor.execute(command, delayInMillis);

        ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);

        verify(executor).execute(captor.capture());

        DeferredRunnable capturedValue = (DeferredRunnable) captor.getValue();
        assertNotNull(capturedValue);
        assertEquals(deferrer, capturedValue.getDeferrer());
        assertEquals(command, capturedValue.getRunnable());
        assertEquals(delayInMillis, capturedValue.getDelayInMillis());
    }

}
