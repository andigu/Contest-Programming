package dmopc16c1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * @author Andi Gu
 */
public class dmopc16c1p5 {
    private static int max(int[] arr) {
        int ans = arr[0];
        for (int i : arr) {
            if (i > ans) {
                ans = i;
            }
        }
        return ans;
    }

    private static long[] findLower(int[] arr) {
        int max = max(arr) + 1;
        BinaryIndexedTree tree = new BinaryIndexedTree(max);
        long[] left = new long[arr.length];
        for (int i=0; i < arr.length; i++) {
            left[i] = tree.sum(arr[i]);
            tree.add(arr[i], 1);
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        String[] tokens = reader.readLine().split(" ");
        int[] arr = new int[n];
        for (int i=0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        LinkedList<Integer> list = new LinkedList<>();
        long[] lower = findLower(arr);
        int index = 0;
        int ans = 0;
        for (int number : arr) {
            if (list.isEmpty()) {
                list.add(number);
            }
            else {
                if (number < list.peek()) {
                    list.addFirst(number);
                    ans += lower[index];
                }
                else if (number > list.peekLast()) {
                    list.addLast(number);
                    ans += index - lower[index];
                }
                else {
                    long count = lower[index];
                    if (count > index - count) {
                        list.addLast(number);
                        ans += index - lower[index];
                    }
                    else {
                        list.addFirst(number);
                        ans += lower[index];
                    }
                }
            }
            index ++;
        }
        System.out.println(ans);
    }
}

class BinaryIndexedTree {
    private long data[];

    public BinaryIndexedTree(int size) {
        data = new long[size + 1];
    }

    public void add(int i, int val) {
        while (i < data.length) {
            data[i] += val;
            i |= i + 1;
        }
    }

    public long sum(int i) {
        long sum = 0;
        while (i >= 0) {
            sum += data[i];
            i &= i + 1;
            i -= 1;
        }
        return sum;
    }
}
