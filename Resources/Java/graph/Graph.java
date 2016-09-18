package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andi Gu
 */
public class Graph<T> {
    private Map<T, Vertex<T>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public void addVertex(T id) {
        if (!hasVertex(id)) {
            vertices.put(id, new Vertex<T>(id));
        }
    }

    public void removeVertex(T id) {
        if (hasVertex(id)) {
            vertices.remove(id);
        }
    }

    public void addEdge(T vertexA, T vertexB, int weight) {
        if (!vertices.get(vertexA).hasNeighbour(vertices.get(vertexB))) {
            vertices.get(vertexA).addNeighbour(vertices.get(vertexB), weight);
        }
    }

    public void removeEdge(T vertexA, T vertexB) {
        if (vertices.get(vertexA).hasNeighbour(vertices.get(vertexB))) {
            vertices.get(vertexA).removeNeighbour(vertices.get(vertexB));
        }
    }

    public int getWeight(T vertexA, T vertexB) {
        return vertices.get(vertexA).getWeight(vertices.get(vertexB));
    }

    public boolean hasVertex(T id) {
        return vertices.containsKey(id);
    }
}
