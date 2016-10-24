import math.MatrixMultiplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Andi Gu
 */
public class test {
    private static List<int[][]> generateMatrices(int n) {
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
        int n = 3;
        List<int[][]> matrices = generateMatrices(n);
        MatrixMultiplier multiplier = new MatrixMultiplier();
        System.out.println(multiplier.multiply(matrices.toArray(new int[][][]{})).toString());
    }
}
