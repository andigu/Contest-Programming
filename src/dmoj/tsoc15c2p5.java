package dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Andi Gu
 */
public class tsoc15c2p5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final int speed = Integer.parseInt(reader.readLine().split(" ")[0]);
        int n = Integer.parseInt(reader.readLine().split(" ")[0]);
        int[][] data = new int[n][4];
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" ");
            data[i] = new int[]{Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), i};
        }
        Comparator<int[]> comparator = (o1, o2) -> {
            int result = 0;
            if (o2[0] != o1[0]) {
                result = Integer.compare(o2[0], o1[0]);
            }
            else if (o2[0] >= speed && o1[0] >= speed) {
                result = Integer.compare(o2[2], o1[2]);
            }
            else if (o2[0] < speed && o1[0] < speed) {
                result = Integer.compare(o2[1], o1[1]);
            }
            if (result == 0) {
                result = Integer.compare(o2[3], o1[3]);
            }
            return result;
        };
        Arrays.parallelSort(data, comparator);
        int queries = Integer.parseInt(reader.readLine().split(" ")[0]);
        for (int i=0; i < queries; i++) {
            int query = Integer.parseInt(reader.readLine().split(" ")[0]) - 1;
            System.out.println(data[query][3] + 1);
        }
    }
}
