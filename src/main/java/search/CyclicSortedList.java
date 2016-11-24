package search;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * List which can speed up some operations based on a fact that underlying
 * array is cyclically sorted.
 */
public class CyclicSortedList<T extends Comparable<T>> implements List<T> {
    private final List<T> underlying;
    private int offset;
    private boolean offsetIsFound;

    public CyclicSortedList(List<T> underlying) {
        this.underlying = underlying;
    }

    @Override
    public boolean contains(Object o) {
        return underlying.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return underlying.iterator();
    }

    @Override
    public Object[] toArray() {
        return underlying.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return underlying.toArray(a);
    }

    @Override
    public int size() {
        return underlying.size();
    }

    @Override
    public boolean isEmpty() {
        return underlying.isEmpty();
    }

    public boolean add(T t) {
        return underlying.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return underlying.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return underlying.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return underlying.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return underlying.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return underlying.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return underlying.retainAll(c);
    }

    @Override
    public void clear() {
        underlying.clear();
    }

    @Override
    public T get(int index) {
        return underlying.get((index + getOffset()) % underlying.size());
    }

    public int getOffset() {
        if (offsetIsFound) {
            return offset;
        } else {
            return computedOffset();
        }
    }

    private int computedOffset() {
        offset = findInRange(0, underlying.size() - 1);
        offsetIsFound = true;
        return offset;
    }

    private int findInRange(int start, int end) {

        if (start >= end) {
            if (start + 1 < underlying.size() && underlying.get(start + 1).compareTo(underlying.get(start)) < 1) {
                return start + 1;
            } else {
                return start;
            }
        }

        int mid = (end + start ) / 2;
        if (underlying.get(start).compareTo(underlying.get(mid)) < 0) {
            // part to the left of mid point is sorted so no need to look for offset there
            return findInRange(mid + 1, end);
        } else {
            // part the the left contains offset point
            return findInRange(start, mid - 1);
        }
    }

    public T set(int index, T element) {
        return underlying.set(index, element);
    }

    public void add(int index, T element) {
        underlying.add(index, element);
    }

    @Override
    public T remove(int index) {
        return underlying.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return underlying.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return underlying.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return underlying.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return underlying.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return underlying.subList(fromIndex, toIndex);
    }

}
