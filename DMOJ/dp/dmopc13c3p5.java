package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Andi Gu
 */
public class dmopc13c3p5 {
    public static void main(String[] tokens) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        tokens = reader.readLine().split(" ");
        int timeLimit = Integer.parseInt(tokens[0]);
        int foodLimit = Integer.parseInt(tokens[1]);
        int n = Integer.parseInt(tokens[2]);
        int[][] r = new int[n][3];
        for (int i = 0; i < n; i++) {
            tokens = reader.readLine().split(" ");
            r[i][0] = Integer.parseInt(tokens[0]);
            r[i][1] = Integer.parseInt(tokens[1]);
            r[i][2] = Integer.parseInt(tokens[2]);
        }
        int[][][] dp = new int[n + 1][foodLimit + 1][timeLimit + 1];
        for (int i = 1; i <= n; i++) {
            for (int f = 1; f <= foodLimit; f++) {
                for (int t = 1; t <= timeLimit; t++) {
                    if (r[i - 1][1] > t || r[i - 1][2] > f) {
                        dp[i][f][t] = dp[i - 1][f][t];
                    } else {
                        dp[i][f][t] = Math.max(dp[i - 1][f][t],
                                dp[i - 1][f - r[i - 1][2]][t - r[i - 1][1]] + r[i - 1][0]);
                    }
                }
            }
        }
        System.out.println(dp[n][foodLimit][timeLimit]);
    }
}
