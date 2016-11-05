package resources.graph.algorithm;

import resources.ds.collections.PriorityPair;
import resources.ds.collections.PriorityQueue;
import resources.graph.Graph;
import resources.graph.Vertex;

import java.util.*;

/**
 * @author Andi Gu
 */
class Dijkstra<T> {
    private Graph<T> graph;

    Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    Map<T, Integer> shortestDist(T startId) {
        PriorityQueue<Integer, Vertex<T>> queue = new PriorityQueue<>(Integer::compareTo);
        Map<T, Integer> distance = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        for (Vertex<T> vertex : graph.getVertices()) {
            queue.push(new PriorityPair<>(Integer.MAX_VALUE, vertex));
            distance.put(vertex.getId(), Integer.MAX_VALUE);
        }
        queue.changePriority(graph.getVertex(startId), 0);
        Vertex<T> current = queue.pop().getValue();
        distance.put(current.getId(), 0);
        while (!queue.isEmpty() && visited.size() < graph.getSize()) {
            for (Vertex<T> neighbour : current.getNeighbours()) {
                if (!visited.contains(neighbour)) {
                    int tentative = distance.get(current.getId()) + current.getWeight(neighbour);
                    if (tentative < distance.get(neighbour.getId())) {
                        queue.changePriority(neighbour, tentative);
                        distance.put(neighbour.getId(), tentative);
                    }
                }
            }
            visited.add(current);
            current = queue.pop().getValue();
        }
        return distance;
    }
}
