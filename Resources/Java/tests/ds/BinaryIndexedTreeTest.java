package ds;

import ds.tree.BinaryIndexedTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andi Gu
 */
public class BinaryIndexedTreeTest {
    private int[] array = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
    private BinaryIndexedTree tree = new BinaryIndexedTree(array, array.length);

    @Test
    public void testBinaryIndexedTree() {
        long[] expected = {0, 2, 3, 1, 7, 2, 5, 4, 21, 6, 13, 8, 30};
        Assert.assertArrayEquals(expected, tree.getData());
    }

    @Test
    public void testAdd() {
        tree.add(3, 6);
        long[] expected = {0, 2, 3, 1, 13, 2, 5, 4, 27, 6, 13, 8, 30};
        Assert.assertArrayEquals(expected, tree.getData());
    }

    @Test
    public void testSum() {
        long expected = 12;
        long sum = tree.sum(5);
        Assert.assertEquals(expected, sum);

        tree.add(3, 6);
        expected = 18;
        sum = tree.sum(5);
        Assert.assertEquals(expected, sum);
    }
}
