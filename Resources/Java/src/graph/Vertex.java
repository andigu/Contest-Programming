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

    Vertex(T id) {
        this.id = id;
        edges = new HashMap<>();
    }

    public T getId() {
        return id;
    }

    public void addNeighbour(Vertex<T> vertex) {
        edges.put(vertex, 1);
    }

    void addNeighbour(Vertex<T> vertex, int weight) {
        edges.put(vertex, weight);
    }

    void removeNeighbour(Vertex<T> vertex) {
        edges.remove(vertex);
    }

    boolean hasNeighbour(Vertex<T> vertex) {
        return edges.containsKey(vertex);
    }

    public Set<Vertex<T>> getNeighbours() {
        return edges.keySet();
    }

    public int getWeight(Vertex<T> neighbour) {
        return hasNeighbour(neighbour) ? edges.get(neighbour) : -1;
    }

    public String toString() {
        String result = id + " {";
        for (Vertex<T> vertex : getNeighbours()) {
            result += vertex.getId() + ": " + getWeight(vertex);
        }
        result += "}";
        return result;
    }
}
