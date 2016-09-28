package ds;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Andi Gu
 */
public class coci083p5 {
    private static long max(long a, long b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine().trim());
        int[] nodes = new int[n];
        for (int i=0; i < n; i++) {
            nodes[i] = Integer.parseInt(reader.readLine().trim());
        }
        reader.close();
        long[] depth = new long[n + 1];
        long total = 0;
        List<Integer> processed = new ArrayList<>();
        for (int i=0; i < n; i++) {
            int node = nodes[i];
            int lower = -(Collections.binarySearch(processed, node) + 1) - 1;
            int upper = lower + 1;
            if (lower == -1 && upper == i) {
                depth[node] = 0;
            }
            else if (upper == i) {
                depth[node] = depth[processed.get(lower)] + 1;
            }
            else if (lower == -1) {
                depth[node] = depth[processed.get(upper)] + 1;
            }
            else {
                depth[node] = max(depth[processed.get(upper)], depth[processed.get(lower)]) + 1;
            }
            processed.add(upper, node);
            total += depth[node];
            writer.write(String.valueOf(total));
            writer.newLine();
        }
        writer.close();
    }
}
