package resources.java.ds.tree.binary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Andi Gu
 */

public class BinaryHeap<E> {
    protected List<E> data;
    private Comparator<E> comparator; // Comparator that returns positive if o2 > o1 gives a max heap (returns o2-o1)
    protected int size;

    public BinaryHeap(Comparator<E> comparator) {
        data = new ArrayList<>();
        data.add(null);
        this.comparator = comparator;
        size = 0;
    }

    public void push(E value) {
        data.add(value);
        shiftUp(++size);
    }

    public E pop() {
        E result = data.get(1);
        data.set(1, data.get(size));
        data.remove(size--);
        shiftDown(1);
        return result;
    }

    public E peek() {
        return data.get(1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected void shiftUp(int index) {
        while (index / 2 > 0 && comparator.compare(data.get(index), data.get(index / 2)) < 0) {
            swap(index, index / 2);
            index /= 2;
        }
    }

    protected void shiftDown(int index) {
        int child = index * 2 + 1 > size || compare(index * 2, index * 2 + 1) <= 0 ? index * 2 : index * 2 + 1;
        while (index * 2 <= size && compare(index, child) > 0) {
            swap(index, child);
            index = child;
            child = index * 2 + 1 > size || compare(index * 2, index * 2 + 1) <= 0 ? index * 2 : index * 2 + 1;
        }
    }

    protected void swap(int indexA, int indexB) {
        E temp = data.get(indexA);
        data.set(indexA, data.get(indexB));
        data.set(indexB, temp);
    }

    public int getSize() {
        return size;
    }

    private int compare(int indexA, int indexB) {
        return comparator.compare(data.get(indexA), data.get(indexB));
    }
}