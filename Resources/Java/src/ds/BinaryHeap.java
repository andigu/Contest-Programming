package ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Andi Gu
 */

public class BinaryHeap<T extends Comparable<T>> {
    private List<T> data;
    private int size;

    public BinaryHeap() {
        data = new ArrayList<>();
        data.add(null);
        size = 0;
    }

    public void push(T value) {
        data.add(value);
        shiftUp(++size);
    }

    public T pop() {
        T result = data.get(1);
        data.set(1, data.get(size));
        data.remove(size--);
        shiftDown(1);
        return result;
    }

    public T peek() {
        return data.get(1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void shiftUp(int index) {
        while (index / 2 > 0) {
            if (data.get(index).compareTo(data.get(index / 2)) < 0) {
                swap(index, index / 2);
            }
            index /= 2;
        }
    }

    public void shiftDown(int index) {
        int minChild;
        while (index * 2 < size) {
            minChild = minChildIndex(index);
            if (data.get(index).compareTo(data.get(minChild)) > 0) {
                swap(index, minChild);
            }
            index = minChild;
        }
    }

    private int minChildIndex(int index) {
        if (index * 2 + 1 > size) {
            return index * 2 + 1;
        } else if (data.get(index * 2).compareTo(data.get(index * 2 + 1)) >= 0) {
            return index * 2 + 1;
        } else {
            return index * 2;
        }
    }

    private void swap(int indexA, int indexB) {
        T temp = data.get(indexA);
        data.set(indexA, data.get(indexB));
        data.set(indexB, temp);
    }

    public static void main(String[] args) {
        Random random = new Random();
        int x = 12000;
        long[] sorted = new long[x];
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        for (int i = 0; i < x; i ++) {
            int a = random.nextInt(x);
            heap.push(a);
            sorted[i] = a;
        }
        Arrays.sort(sorted);
        long [] b = new long[x];
        for (int i=0; i < x; i ++) {
            b[i] = heap.pop();
        }
        if (Arrays.equals(b, sorted)) {
            for (int i=0; i < x; i++) {
                if (b[i] != sorted[i]) {
                    System.out.println(i + " " + b[i] + " " + sorted[i]);
                }
            }
        }
        else {
            System.out.println(true);
        }
    }
}