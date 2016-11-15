package resources.java.ds.collections;

import resources.java.ds.tree.binary.BinaryHeap;

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
        super((o1, o2) -> priorityComparator.compare(o1.getPriority(), o2.getPriority()));
        this.priorityComparator = priorityComparator;
        index = new HashMap<>();
    }

    @Override
    public void push(PriorityPair<P, T> value) {
        super.push(value);
        index.put(value.getValue(), size);
    }

    @Override
    public PriorityPair<P, T> pop() {
        index.put(data.get(size).getValue(), 1);
        PriorityPair<P, T> popped = super.pop();
        index.remove(popped.getValue());
        return popped;
    }

    @Override
    protected void swap(int indexA, int indexB) {
        index.put(data.get(indexA).getValue(), indexB);
        index.put(data.get(indexB).getValue(), indexA);
        PriorityPair<P, T> temp = data.get(indexA);
        data.set(indexA, data.get(indexB));
        data.set(indexB, temp);
    }

    public void changePriority(T element, P newPriority) {
        int index = this.index.get(element);
        int compareResult = priorityComparator.compare(newPriority, data.get(index).getPriority());
        data.get(index).setPriority(newPriority);
        if (compareResult < 0) {
            shiftUp(index);
        } else if (compareResult > 0) {
            shiftDown(index);
        }
    }

    public List<PriorityPair<P, T>> getElements() {
        return data;
    }
}
