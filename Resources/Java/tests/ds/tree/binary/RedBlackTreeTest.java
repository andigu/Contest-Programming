package ds.tree.binary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andi Gu
 */
public class RedBlackTreeTest {
    private Integer[] array = {9, 2, 7, 8, 1, 3, 12, 11, 10, 90};
    private RedBlackTree<Integer> tree;
    private RedBlackTree<Integer> emptyTree;

    @Before
    public void setUp() throws Exception {
        emptyTree = new RedBlackTree<>();
        tree = new RedBlackTree<>(array);
    }

    @Test
    public void testInsert() {
        int expectedSize = emptyTree.getSize();
        for (int integer : array) {
            propertyCheck(emptyTree);
            emptyTree.insert(integer);
            Assert.assertEquals(emptyTree.getSize(), ++expectedSize);
        }
    }

    @Test
    public void testDelete() {
        int expectedSize = tree.getSize();
        for (int integer : array) {
            propertyCheck(tree);
            tree.delete(integer);
            Assert.assertEquals(tree.getSize(), --expectedSize);
        }
    }

    private void propertyCheck(RedBlackTree<Integer> tree) {
        Assert.assertTrue(blackRoot(tree));
        Assert.assertTrue(blackChildren(tree.getRoot()));
        List<RedBlackNode<Integer>> leaves = new ArrayList<>();
        findLeafNodes(tree.getRoot(), leaves);
        if (leaves.size() > 0) {
            int blackHeight = blackDepth(leaves.get(0));
            for (RedBlackNode<Integer> leaf : leaves) {
                Assert.assertEquals(blackHeight, blackDepth(leaf));
            }
        }
        Assert.assertTrue(height(tree.getRoot()) <= getExpectedHeight(tree));
    }

    // Every red black tree should have max height of 2 log_2(n)
    private int getExpectedHeight(RedBlackTree<Integer> tree) {
        return 2 * ((int) Math.round(Math.log(tree.getSize()) / Math.log(2))) + 1;
    }

    private int height(RedBlackNode<Integer> node) {
        if (node == null) return 0;
        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    private boolean blackRoot(RedBlackTree<Integer> tree) {
        return tree.getRoot() == null || tree.getRoot().isBlack();
    }

    // Check that all children of red nodes are black
    private boolean blackChildren(RedBlackNode<Integer> node) {
        if (node == null) {
            return true;
        }
        else if (node.isRed()) {
            return (node.getLeft() == null || node.getLeft().isBlack()) &&
                    (node.getRight() == null || node.getRight().isBlack()) &&
                    blackChildren(node.getLeft()) && blackChildren(node.getRight());
        }
        else {
            return blackChildren(node.getLeft()) && blackChildren(node.getRight());
        }
    }

    private void findLeafNodes(RedBlackNode<Integer> node, List<RedBlackNode<Integer>> leaves) {
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                leaves.add(node);
            } else {
                findLeafNodes(node.getLeft(), leaves);
                findLeafNodes(node.getRight(), leaves);
            }
        }
    }

    private int blackDepth(RedBlackNode<Integer> node) {
        if (node == null) {
            return 0;
        }
        else if (node.isBlack()) {
            return blackDepth(node.getParent()) + 1;
        }
        else {
            return blackDepth(node.getParent());
        }
    }
}