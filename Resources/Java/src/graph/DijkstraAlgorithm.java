package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Andi Gu
 */
public class DijkstraAlgorithm<T> {
    private T smallestDist(Map<T, Double> distance, Set<T> unvisited) {
        Double best = Double.POSITIVE_INFINITY;
        T bestId = null;
        for (Map.Entry<T, Double> entry : distance.entrySet()) {
            if (entry.getValue() < best && unvisited.contains(entry.getKey())) {
                bestId = entry.getKey();
                best = entry.getValue();
            }
        }
        return bestId;
    }

    public Map<T, Double> Dijkstra(Graph<T> graph, T current) {
        Map<T, Double> distances = new HashMap<>();
        for (T id : graph.getIds()) {
            distances.put(id, Double.POSITIVE_INFINITY);
        }
        distances.put(current, 0d);
        Set<T> unvisited = new HashSet<>(graph.getIds());
        current = smallestDist(distances, unvisited);
        while (!unvisited.isEmpty() && current != null) {
            unvisited.remove(current);
            for (Vertex<T> neighbour : graph.getVertex(current).getNeighbours()) {
                double temp = distances.get(current) + graph.getWeight(current, neighbour.getId());
                if (temp < distances.get(neighbour.getId())) {
                    distances.put(neighbour.getId(), temp);
                }
            }
            current = smallestDist(distances, unvisited);
        }
        return distances;
    }
}
