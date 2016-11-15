package resources.java.graph.algorithm;

import resources.java.ds.collections.PriorityPair;
import resources.java.ds.collections.PriorityQueue;
import resources.java.graph.Graph;
import resources.java.graph.Vertex;

import java.util.*;

/**
 * @author Andi Gu
 */
public class Dijkstra<T> {
    private Graph<T> graph;

    public Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    public Map<T, Double> shortestDist(T startId) {
        PriorityQueue<Double, Vertex<T>> queue = new PriorityQueue<>(Double::compareTo);
        Map<T, Double> distance = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        for (Vertex<T> vertex : graph.getVertices()) {
            queue.push(new PriorityPair<>(Double.POSITIVE_INFINITY, vertex));
            distance.put(vertex.getId(), Double.POSITIVE_INFINITY);
        }
        queue.changePriority(graph.getVertex(startId), 0d);
        distance.put(startId, 0d);
        Vertex<T> current;
        while (!queue.isEmpty()) {
            current = queue.pop().getValue();
            visited.add(current);
            for (Vertex<T> neighbour : current.getNeighbours()) {
                if (!visited.contains(neighbour)) {
                    double tentative = distance.get(current.getId()) + current.getWeight(neighbour);
                    if (tentative < distance.get(neighbour.getId())) {
                        queue.changePriority(neighbour, tentative);
                        distance.put(neighbour.getId(), tentative);
                    }
                }
            }
        }
        return distance;
    }
}
