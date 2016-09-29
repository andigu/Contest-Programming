package graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Andi Gu
 */
public class cco13p3 {
    private static int[] depth;
    private static Graph<Integer> graph;

    private static void dfs(int id, int currentLevel) {
        depth[id] = currentLevel++;
        for (Vertex<Integer> vertex : graph.getVertex(id).getNeighbours()) {
            if (depth[vertex.getId()] == -1) {
                dfs(vertex.getId(), currentLevel);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        depth = new int[n];
        graph = new Graph<>();
        for (int i = 0; i < n - 1; i++) {
            String[] tokens = reader.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]) - 1, b = Integer.parseInt(tokens[1]) - 1;
            depth[a] = -1;
            graph.addEdge(a, b, true);
        }
        System.out.println(Arrays.toString(depth));
        for (int i = 0; i < n; i++) {
            if (depth[i] == 0) {
                dfs(i, 0);
            }
        }
        System.out.println(Arrays.toString(depth));

    }
}
