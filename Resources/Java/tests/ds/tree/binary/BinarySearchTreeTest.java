package ds.tree.binary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Andi Gu
 */
public class BinarySearchTreeTest {
    private Integer[] array;
    private RedBlackTree<Integer> tree;
    private RedBlackTree<Integer> emptyTree;
    private static final int MAX = 1000;
    private static final int MIN = 0;

    @Before
    public void setUp() throws Exception {
        array = new Integer[]{8, 3, 10, 1, 6, 4, 7, 14, 13, 12, 11};
        tree = new RedBlackTree<>(array);
        emptyTree = new RedBlackTree<>();
    }

    @Test
    public void insert() throws Exception {
        Assert.assertTrue(isValid(tree));
        int[] array = {8, 3, 10, 1, 6, 4, 7, 14, 13, 12, 11};
        int expectedSize = tree.getSize();
        for (int i : array) {
            tree.insert(i);
            Assert.assertEquals(++expectedSize, tree.getSize());
            Assert.assertTrue(isValid(tree));
        }
        Assert.assertTrue(isValid(tree));
    }

    @Test
    public void find() throws Exception {
        for (Integer i : array) {
            Assert.assertEquals(i, tree.find(i).getData());
        }
        Assert.assertEquals(tree.find(10000), null);
    }

    @Test
    public void delete() throws Exception {
        int expectedSize = tree.getSize();
        for (Integer i : array) {
            tree.delete(i);
            Assert.assertEquals(--expectedSize, tree.getSize());
            Assert.assertTrue(isValid(tree));
        }
        tree.delete(1000);
        Assert.assertEquals(tree.getSize(), 0);
    }

    private boolean isValid(BinarySearchTree<Integer> tree) {
        return isValidBST(tree.getRoot(), MIN, MAX);
    }

    private boolean isValidBST(Node<Integer> node, int min, int max) {
        return node == null ||
                (node.getData() >= min && node.getData() <= max
                        && isValidBST(node.getLeft(), min, node.getData())
                        && isValidBST(node.getRight(), node.getData(), max));
    }
}