package graph;

/**
 * @author Andi Gu
 */
public class Test {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(3, 4, 5);
        System.out.println(graph.toString());
    }
}
