package math;

/**
 * @author Andi Gu
 */
public class MatrixMultiplier {
    private int[][] matrixChainOrder(Matrix[] matrices) {
        int n = matrices.length;
        int[] dimensions = new int[n * 2];
        for (int i = 0; i < n; i++) {
            dimensions[i * 2] = matrices[i].getHeight();
            dimensions[i * 2 + 1] = matrices[i].getWidth();
        }
        int[][] dp = new int[n][n];
        int[][] result = new int[n][n];
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        result[i][j] = k;
                    }
                }
            }
        }
        return result;
    }

    public Matrix multiply(int[][][] data) {
        Matrix[] matrices = new Matrix[data.length];
        for (int i = 0; i < data.length; i++) {
            matrices[i] = new Matrix(data[i]);
        }
        return multiply(matrices);
    }

    private Matrix multiply(Matrix[] matrices) {
        int[][] order = matrixChainOrder(matrices);
        return chainMultiply(matrices, order, 0, order.length - 1);
    }

    private Matrix chainMultiply(Matrix[] matrices, int[][] order, int i, int j) {
        if (i == j) {
            return matrices[i];
        } else {
            return chainMultiply(matrices, order, i, order[i][j]).multiply(chainMultiply(matrices, order, order[i][j] + 1, j));
        }
    }

}
