package ds;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Andi Gu
 */
public class BinaryIndexedTree {
    private int size;
    private long data[];

    public BinaryIndexedTree(int size) {
        this.size = size + 1;
        data = new long[this.size];
    }

    public BinaryIndexedTree(long[] array, int size) {
        this.size = size + 1;
        data = new long[this.size];
        data[0] = 0;
        for (int i = 1; i < this.size; i++) {
            data[i] += array[i - 1];
            int i2 = i + (i & -i);
            if (i2 < this.size) {
                data[i2] += data[i];
            }
        }
    }

    public BinaryIndexedTree(int[] array, int size) {
        this.size = size + 1;
        data = new long[this.size];
        data[0] = 0;
        for (int i = 1; i < this.size; i++) {
            data[i] += array[i - 1];
            int i2 = i + (i & -i);
            if (i2 < this.size) {
                data[i2] += data[i];
            }
        }
    }

    public void add(int i, int val) {
        while (i <= size) {
            data[i] += val;
            i += (-i & i);
        }
    }

    public long sum(int i) {
        long sum = 0;
        while (i > 0) {
            sum += data[i];
            i -= (i & -i);
        }
        return sum;
    }

    public long[] getData() {
        return data;
    }
}
