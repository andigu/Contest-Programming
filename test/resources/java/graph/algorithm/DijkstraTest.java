package resources.java.graph.algorithm;

import resources.java.graph.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.java.graph.algorithm.Dijkstra;

import java.util.Map;

/**
 * @author Andi Gu
 */
public class DijkstraTest {
    private Dijkstra<Integer> dijkstra;

    @Before
    public void setUp() throws Exception {
        Graph<Integer> graph = new Graph<>();
        for (int i=0; i < 4; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 2);
        dijkstra = new Dijkstra<>(graph);
    }

    @Test
    public void shortestDist() throws Exception {
        Map<Integer, Double> result = dijkstra.shortestDist(0);
        Assert.assertEquals(new Double(0), result.get(0));
        Assert.assertEquals(new Double(2), result.get(1));
        Assert.assertEquals(new Double(4), result.get(2));
        Assert.assertEquals(new Double(Double.POSITIVE_INFINITY), result.get(3));
    }

}