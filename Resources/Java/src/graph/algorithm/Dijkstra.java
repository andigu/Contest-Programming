package graph.algorithm;

import ds.collections.PriorityPair;
import ds.collections.PriorityQueue;
import graph.Graph;
import graph.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Andi Gu
 */
public class Dijkstra<T> {
    public Map<T, Integer> shortestDist(Graph<T> graph, T startId) {
        PriorityQueue<Integer, Vertex<T>> queue = new PriorityQueue<>(Integer::compareTo);
        Set<Vertex<T>> visited = new HashSet<>();
        for (Vertex<T> vertex : graph.getVertices()) {
            queue.push(new PriorityPair<>(Integer.MAX_VALUE, vertex));
        }
        Vertex<T> current = graph.getVertex(startId);
        queue.changePriority(current, 0);
        while (visited.size() < graph.getSize()) {
            for (Vertex<T> neighbour : current.getNeighbours()) {
                if (!visited.contains(neighbour)) {
                    int tentative = queue.getPriority(current) + current.getWeight(neighbour);
                    if (tentative < queue.getPriority(neighbour)) {
                        queue.changePriority(neighbour, tentative);
                    }
                }
            }
            visited.add(current);
            current = queue.pop().getValue();
        }
        Map<T, Integer> distance = new HashMap<>();
        for (PriorityPair<Integer, Vertex<T>> pair : queue.getElements()) {
            distance.put(pair.getValue().getId(), pair.getPriority());
        }
        return distance;
    }
}
