package resources.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Andi Gu
 */
public class Graph<T> {
    private Map<T, Vertex<T>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public void addVertex(T id) {
        if (!vertices.containsKey(id)) {
            vertices.put(id, new Vertex<>(id));
        }
    }

    public void removeVertex(T id) {
        vertices.remove(id);
    }

    public void addEdge(T vertexA, T vertexB, int weight) {
        addEdge(vertexA, vertexB, weight, true);
    }

    public void addEdge(T vertexA, T vertexB, int weight, boolean bidirectional) {
        addVertex(vertexA);
        addVertex(vertexB);

        vertices.get(vertexA).addNeighbour(vertices.get(vertexB), weight);
        if (bidirectional) {
            vertices.get(vertexB).addNeighbour(vertices.get(vertexA), weight);
        }
    }

    public void addEdge(T vertexA, T vertexB, boolean bidirectional) {
        addEdge(vertexA, vertexB, 1, bidirectional);
    }

    public void removeEdge(T vertexA, T vertexB) {
        if (vertices.get(vertexA).hasNeighbour(vertices.get(vertexB))) {
            vertices.get(vertexA).removeNeighbour(vertices.get(vertexB));
        }
    }

    public int getWeight(T vertexA, T vertexB) {
        return vertices.get(vertexA).getWeight(vertices.get(vertexB));
    }

    public Set<T> getIds() {
        return vertices.keySet();
    }

    public Vertex<T> getVertex(T id) {
        return vertices.get(id);
    }

    public String toString() {
        String result = "";
        for (Vertex<T> vertex : vertices.values()) {
            result += vertex.toString() + "\n";
        }
        return result;
    }

    public Collection<Vertex<T>> getVertices() {
        return vertices.values();
    }

    public int getSize() {
        return vertices.size();
    }
}
