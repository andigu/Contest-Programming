package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Andi Gu
 */
public class Vertex<T> {
    private T id;
    private Map<Vertex<T>, Integer> edges;

    public Vertex(T id) {
        this.id = id;
        edges = new HashMap<>();
    }

    public T getId() {
        return id;
    }

    public void addNeighbour(Vertex<T> vertex) {
        edges.put(vertex, 1);
    }

    public void addNeighbour(Vertex<T> vertex, int weight) {
        edges.put(vertex, weight);
    }

    public void removeNeighbour(Vertex<T> vertex) {
        edges.remove(vertex);
    }

    public boolean hasNeighbour(Vertex<T> vertex) {
        return edges.containsKey(vertex);
    }

    public Set<Vertex<T>> getNeighbours() {
        return edges.keySet();
    }

    public int getWeight(Vertex<T> vertex) {
        return hasNeighbour(vertex) ? edges.get(vertex) : -1;
    }
}
