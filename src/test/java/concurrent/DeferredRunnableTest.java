package concurrent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * User: goldberg333
 * Date: 22.10.11
 */
public class DeferredRunnableTest {

    private DeferredRunnable deferredRunnable;
    @Mock
    private Deferrer deferrer;
    private int delayInMillis;
    @Mock
    private Runnable command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        delayInMillis = 3000;
        deferredRunnable = new DeferredRunnable(deferrer, delayInMillis, command);
    }

    @Test
    public void testThatRunMethodIsExecutedAfterDeferrer() throws Exception {
        deferredRunnable.run();

        InOrder inOrder = inOrder(deferrer, command);
        inOrder.verify(deferrer).defer(delayInMillis);
        inOrder.verify(command).run();
    }
}
