package resources.java.ds.tree.binary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.java.ds.tree.binary.BinarySearchTree;
import resources.java.ds.tree.binary.Node;

/**
 * @author Andi Gu
 */
public class BinarySearchTreeTest {
    private Integer[] array = {9, 2, 7, 8, 1, 3, 12, 11, 10, 90};
    private BinarySearchTree<Integer> tree;
    private BinarySearchTree<Integer> emptyTree;
    private static final int MAX = 1000;
    private static final int MIN = 0;

    @Before
    public void setUp() throws Exception {
        tree = new BinarySearchTree<>(array);
        emptyTree = new BinarySearchTree<>();
    }

    @Test
    public void testInsert() throws Exception {
        Assert.assertTrue(isValid(emptyTree));
        int[] array = {8, 3, 10, 1, 6, 4, 7, 14, 13, 12, 11};
        int expectedSize = emptyTree.getSize();
        for (int i : array) {
            emptyTree.insert(i);
            Assert.assertEquals(++expectedSize, emptyTree.getSize());
            Assert.assertTrue(isValid(emptyTree));
        }
        Assert.assertTrue(isValid(emptyTree));
    }

    @Test
    public void testFind() throws Exception {
        for (Integer i : array) {
            Assert.assertEquals(i, tree.find(i).getData());
        }
        Assert.assertEquals(tree.find(10000), null);
    }

    @Test
    public void testDelete() throws Exception {
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