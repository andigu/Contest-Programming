import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Andi Gu
 */
public class test {
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    public static List<int[][]> generateMatrices(int n) {
        Random random = new Random();
        List<int[][]> matrices = new ArrayList<>();
        int a = 2, b = 3;
        int last = a + random.nextInt(b);
        for (int i = 0; i < n; i++) {
            int temp = a + random.nextInt(b);
            int[][] matrix = new int[last][temp];
            last = temp;
            for (int[] row : matrix) {
                for (int k = 0; k < row.length; k++) {
                    row[k] = random.nextInt(15);
                }
            }
            matrices.add(matrix);
        }
        return matrices;
    }

    public static void main(String[] args) {
        MatrixOrderOptimization optimization = new MatrixOrderOptimization();
        optimization.matrixChainOrder(new int[]{10, 30, 30, 5, 5, 60});
        System.out.println(Arrays.deepToString(optimization.s));
        optimization.printOptimalParenthesizations();
        for (int[][] matrix : generateMatrices(3)) {
            System.out.println(Arrays.deepToString(matrix));
        }
    }
}
