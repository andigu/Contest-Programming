package ds.collections;

/**
 * @author Andi Gu
 */
public class PriorityPair<K, T> {
    private K priority;
    private T value;

    public PriorityPair(K priority, T value) {
        this.priority = priority;
        this.value = value;
    }

    public K getPriority() {
        return priority;
    }

    public T getValue() {
        return value;
    }

    public void setPriority(K priority) {
        this.priority = priority;
    }
}