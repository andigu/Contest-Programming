package ds.tree.binary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.java.ds.tree.binary.BinaryHeap;

import java.util.Arrays;

/**
 * @author Andi Gu
 */
public class BinaryHeapTest {
    private BinaryHeap<Integer> heap;
    private int[] toPush;

    @Before
    public void setUp() throws Exception {
        heap = new BinaryHeap<>((o1, o2) -> o1 - o2); // Gives a min heap
        toPush = new int[] {67, 1, 2, 1, 56, 61, 1, 2, 52, 611, 21, 2, 61, 1, 22, 441, 16};

    }

    @Test
    public void push() throws Exception {
        Integer smallest = toPush[0];
        for (int i = 0; i < toPush.length; i++) {
            if (toPush[i] < smallest) {
                smallest = toPush[i];
            }
            heap.push(toPush[i]);
            Assert.assertEquals(smallest, heap.peek());
            Assert.assertEquals(i + 1, heap.getSize());
        }
    }

    @Test
    public void pop() throws Exception {
        for (int value : toPush) {
            heap.push(value);
        }
        int expectedSize = heap.getSize();
        int[] popped = new int[toPush.length];
        int i = 0;
        while (!heap.isEmpty()) {
            popped[i++] = heap.pop();
            Assert.assertEquals(--expectedSize, heap.getSize());
        }
        Arrays.sort(toPush);
        Assert.assertArrayEquals(toPush, popped);
    }
}