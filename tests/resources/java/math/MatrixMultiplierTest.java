package math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.java.math.Matrix;
import resources.java.math.MatrixMultiplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Andi Gu
 */
public class MatrixMultiplierTest {
    private List<Matrix> matrices;

    @Before
    public void setUp() throws Exception {
        matrices = new ArrayList<>();
        matrices.addAll(generateMatrices(3).stream().map(Matrix::new).collect(Collectors.toList()));
    }

    private List<int[][]> generateMatrices(int n) {
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


    @Test
    public void multiply() throws Exception {
        Matrix result = matrices.get(0);
        for (Matrix matrix : matrices.subList(1, matrices.size())) {
            result = result.multiply(matrix);
        }
        MatrixMultiplier multiplier = new MatrixMultiplier();
        Assert.assertEquals(result, multiplier.multiply(matrices));
    }
}