package dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Andi Gu
 */
public class dmopc14c4p6 {
    private static ArrayList<HashMap<Integer, Integer>> dp;
    private static ArrayList<ArrayList<Integer>> connections;

    private static int depth(int node, int parent) {
        if (!dp.get(parent).containsKey(node)) {
            int ans = 0;
            for (int child : connections.get(node)) {
                if (child != parent) {
                    int temp = depth(child, node) + 1;
                    if (temp > ans) {
                        ans = temp;
                    }
                }
            }
            dp.get(parent).put(node, ans);
        }
        return dp.get(parent).get(node);
    }

    public static void main(String[] tokens) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        connections = new ArrayList<>();
        dp = new ArrayList<>();
        for (int i=0; i < n + 1; i++) {
            connections.add(new ArrayList<>());
            dp.add(new HashMap<>());
        }
        for (int i=0; i < n - 1; i ++) {
            tokens = reader.readLine().split(" ");
            Integer a = Integer.parseInt(tokens[0]);
            Integer b = Integer.parseInt(tokens[1]);
            connections.get(a).add(b);
            connections.get(b).add(a);
        }
        for (int i=1; i <= n; i++) {
            System.out.println(depth(i, 0) + 1);
        }
    }
}