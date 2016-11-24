package search;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link CyclicSortedList}
 */
public class CyclicSortedListTest {

    private CyclicSortedList<Integer> cyclicSortedList;

    @Before
    public void setUp() throws Exception {
        cyclicSortedList = new CyclicSortedList<Integer>(new ArrayList<Integer>());
    }

    @Test
    public void testAdapterReturnsCorrectElementForSingletonList() throws Exception {
        Integer someValue = 333;
        cyclicSortedList.add(someValue);
        assertEquals(someValue, cyclicSortedList.get(0));
    }

    @Test
    public void testAdapterReturnsCorrectElementForSortedListOfTwoElements() throws Exception {
        Integer someValue = 333;
        cyclicSortedList.add(111);
        cyclicSortedList.add(someValue);
        assertEquals(someValue, cyclicSortedList.get(1));
    }

    @Test
    public void testAdapterReturnsCorrectElementForCyclicSortedListOfTwoElements() throws Exception {
        Integer someValue = 333;
        cyclicSortedList.add(someValue);
        cyclicSortedList.add(111);
        assertEquals(someValue, cyclicSortedList.get(1));
    }

    @Test
    public void testAdapterReturnsCorrectElementForCyclicSortedListOfFiveElements() throws Exception {
        Integer someValue = 333;
        cyclicSortedList.add(222);
        cyclicSortedList.add(someValue);
        cyclicSortedList.add(555);
        cyclicSortedList.add(111);
        assertEquals(someValue, cyclicSortedList.get(2));
    }

    @Test
    public void testRegularBinarySearchFindsElementUsingAdapterForCyclicSortedLists() {
        Integer someValue = 3;
        cyclicSortedList.add(4);
        cyclicSortedList.add(5);
        cyclicSortedList.add(someValue);
        cyclicSortedList.add(4);

        assertEquals(2, Collections.binarySearch(cyclicSortedList, someValue) + cyclicSortedList.getOffset());
    }

}
