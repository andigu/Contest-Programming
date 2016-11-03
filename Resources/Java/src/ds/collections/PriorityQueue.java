package ds.collections;

import ds.tree.binary.BinaryHeap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andi Gu
 */
public class PriorityQueue<P, T> extends BinaryHeap<PriorityPair<P, T>> {
    private Map<T, Integer> index;
    private Comparator<P> priorityComparator;

    public PriorityQueue(Comparator<P> priorityComparator) {
        index = new HashMap<>();
        this.priorityComparator = priorityComparator;
    }

    @Override
    protected void swap(int indexA, int indexB) {
        index.put(data.get(indexA).getValue(), indexB);
        index.put(data.get(indexB).getValue(), indexA);
        PriorityPair<P, T> temp = data.get(indexA);
        data.set(indexA, data.get(indexB));
        data.set(indexB, temp);
    }

    @Override
    protected int compare(int indexA, int indexB) {
        return priorityComparator.compare(data.get(indexA).getPriority(), data.get(indexB).getPriority());
    }

    public void changePriority(T element, P newPriority) {
        int index = this.index.get(element);
        int compareResult =priorityComparator.compare(newPriority, data.get(index).getPriority());
        if (compareResult < 0) {
            super.shiftUp(index);
        }
        else if (compareResult > 0) {
            super.shiftDown(index);
        }
    }

    public P getPriority(T element) {
        return data.get(index.get(element)).getPriority();
    }

    public List<PriorityPair<P, T>> getElements() {
        return data;
    }
}
