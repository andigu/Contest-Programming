package Java.ds;

import ds.BinaryIndexedTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Andi Gu
 */
public class BinaryIndexedTreeTest {

    @Test
    public void testConstructor() {
        Scanner scanner = new Scanner(System.in);
        int[] a = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        long[] expected = {0, 2, 3, 1, 7, 2, 5, 4, 21, 6, 13, 8, 30};
        BinaryIndexedTree tree = new BinaryIndexedTree(a, a.length);
        Assert.assertArrayEquals(expected, tree.getData());
    }
}
