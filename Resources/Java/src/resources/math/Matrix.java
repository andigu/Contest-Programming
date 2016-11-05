package resources.math;

import java.util.Arrays;

/**
 * @author Andi Gu
 */
public class Matrix {
    private int[][] matrix;
    private int height;
    private int width;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    public void setData(int x, int y, int data) {
        matrix[x][y] = data;
    }

    public int getData(int x, int y) {
        return matrix[x][y];
    }

    public int[] getRow(int x) {
        return matrix[x];
    }

    public Matrix multiply(Matrix multiplier) {
        int[][] result = new int[height][multiplier.getWidth()];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < multiplier.getWidth(); j++) {
                for (int k = 0; k < multiplier.getHeight(); k++) {
                    result[i][j] += matrix[i][k] * multiplier.getData(k, j);
                }
            }
        }
        return new Matrix(result);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String toString() {
        return Arrays.deepToString(matrix);
    }

    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }
        else {
            Matrix matrix = (Matrix) object;
            if (matrix.getWidth() != width || matrix.getHeight() != height) {
                return false;
            }
            else {
                boolean result = true;
                for (int i=0; i < height; i++) {
                    for (int j=0; j < width; j++) {
                        result &= getData(i, j) == matrix.getData(i, j);
                    }
                }
                return result;
            }
        }

    }
}
