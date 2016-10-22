package ds.tree.binary;

import org.junit.Before;

/**
 * @author Andi Gu
 */
public class RedBlackTreeTest extends BinarySearchTreeTest {
    private RedBlackTree<Integer> tree;
    private int[] array = {8, 2, 6, 1, 6406, 1, 307, 104, 5301, 1};

    @Before
    public void setUp() throws Exception {
        for (int integer : array) {
            tree.insert(integer);
        }
    }

    public void propertyChecks() {
        
    }
}