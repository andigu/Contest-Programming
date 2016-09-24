package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Andi Gu
 */
class Vertex<T> {
    private T id;
    private Map<Vertex<T>, Integer> edges;

    Vertex(T id) {
        this.id = id;
        edges = new HashMap<>();
    }

    private T getId() {
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

    private Set<Vertex<T>> getNeighbours() {
        return edges.keySet();
    }

    int getWeight(Vertex<T> vertex) {
        return hasNeighbour(vertex) ? edges.get(vertex) : -1;
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
