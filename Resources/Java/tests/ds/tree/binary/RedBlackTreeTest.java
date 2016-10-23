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

    @Before
    public void setUp() throws Exception {
        tree = new RedBlackTree<>(array);
    }

    public void propertyCheck() {
        Assert.assertTrue(blackRoot(tree));
        Assert.assertTrue(blackChildren(tree.getRoot()));
        List<RedBlackNode<Integer>> leaves = new ArrayList<>();
        findLeafNodes(tree.getRoot(), leaves);
        int blackHeight = blackDepth(leaves.get(0));
        for (RedBlackNode<Integer> leaf : leaves) {
            Assert.assertEquals(blackHeight, blackDepth(leaf));
        }
    }

    @Test
    public void testDelete() {
        int expectedSize = tree.getSize();
        for (int integer : array) {
            propertyCheck();
            tree.delete(integer);
            try {
                Assert.assertEquals(tree.getSize(), --expectedSize);
            }
            catch (AssertionError error) {
                System.out.println("root" + tree.getRoot().getData());
                inorder(tree.getRoot());
                System.out.println(tree.getSize()+ " " + expectedSize);
            }
        }
    }

    public boolean blackRoot(RedBlackTree<Integer> tree) {
        return tree.getRoot().isBlack();
    }

    // Check that all children of red nodes are black
    public boolean blackChildren(RedBlackNode<Integer> node) {
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

    public void findLeafNodes(RedBlackNode<Integer> node, List<RedBlackNode<Integer>> leaves) {
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                leaves.add(node);
            } else {
                findLeafNodes(node.getLeft(), leaves);
                findLeafNodes(node.getRight(), leaves);
            }
        }
    }

    public int blackDepth(RedBlackNode<Integer> node) {
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

    public void inorder(RedBlackNode<Integer> node) {
        if (node != null) {
            inorder(node.getLeft());
            System.out.println(node.getData());
            inorder(node.getRight());
        }
    }
}