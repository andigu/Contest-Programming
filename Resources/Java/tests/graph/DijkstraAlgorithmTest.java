package graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andi Gu
 */
public class DijkstraAlgorithmTest {
    private Graph<Integer> graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph<>();
        for (int i=1; i <= 4; i ++) {
            graph.addVertex(i);
        }
        graph.addEdge(1, 2, 2, true);
        graph.addEdge(1, 3, 5, true);
        graph.addEdge(2, 3, 2, true);
    }

    @Test
    public void testDijkstra() {
        Map<Integer, Double> expected = new HashMap<>();
        expected.put(1, 0d);
        expected.put(2, 2d);
        expected.put(3, 4d);
        expected.put(4, Double.POSITIVE_INFINITY);

        DijkstraAlgorithm<Integer> algorithm = new DijkstraAlgorithm<>();
        Map<Integer, Double> result =  algorithm.Dijkstra(graph, 1);
        for (Map.Entry<Integer, Double> entry : result.entrySet()) {
            int key = entry.getKey();
            Assert.assertEquals(expected.get(key), entry.getValue());
        }
    }
}