package ds.tree.binary;

/**
 * @author Andi Gu
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    public RedBlackTree() {
        super();
    }

    public RedBlackTree(T[] array) {
        super(array);
    }

    public void insert(T data) {
        insert(new RedBlackNode<>(data));
    }

    private void insert(RedBlackNode<T> node) {
        node.makeRed();
        super.insert(node);
        insertAdjust(node);
    }

    /**
     * There are 5 situations:
     * - N is the root node, i.e., first node of red–black tree
     * - N's parent (P) is black
     * - N's parent (P) and uncle (U) are red
     * - N is added to right of left child of grandparent, or N is added to left of right child of grandparent
     * (P is red and U is black)
     * - N is added to left of left child of grandparent, or N is added to right of right child of grandparent
     * (P is red and U is black)
     *
     * @param node The node that was inserted
     */
    private void insertAdjust(RedBlackNode<T> node) {
        if (node.getParent() == null) { // Case 1
            node.makeBlack();
        } else if (node.getParent().isRed()) { // If the parent is black the tree is still valid, so exit (nose Case 2)
            RedBlackNode<T> uncle = node.getParent().getSibling();
            RedBlackNode<T> grandparent = node.getGrandparent();
            if (uncle != null && uncle.isRed()) { // Case 3
                node.getParent().makeBlack();
                uncle.makeBlack();
                grandparent.makeRed();
                insertAdjust(grandparent);
            } else { // Case 4
                if (node.isRightChild() && node.getParent().isLeftChild()) { // Case 4A
                    rotateLeft(node.getParent());
                    node = node.getLeft();
                } else if (node.isLeftChild() && node.getParent().isRightChild()) { // Case 4B
                    rotateRight(node.getParent());
                    node = node.getRight();
                }
                node.getParent().makeBlack();
                grandparent.makeRed();
                if (node.isLeftChild()) {
                    rotateRight(grandparent);
                } else {
                    rotateLeft(grandparent);
                }
            }
        }
    }

    public void delete(T data) {
        super.delete(find(data));
    }

    public void delete(RedBlackNode<T> node) {

    }

    private void deleteAdjust(RedBlackNode<T> node) {

    }

    private void rotateLeft(RedBlackNode<T> node) { // rotate left on given node
        RedBlackNode<T> pivot = node.getRight();
        if (pivot != null) {
            if (node.isLeftChild()) {
                node.getParent().setLeft(pivot);
            }
            else if (node.isRightChild()) {
                node.getParent().setRight(pivot);
            }
            pivot.setParent(node.getParent());

            node.setRight(pivot.getLeft());
            if (pivot.getLeft() != null) {
                pivot.getLeft().setParent(node);
            }
            pivot.setLeft(node);
            node.setParent(pivot);
        }
    }

    private void rotateRight(RedBlackNode<T> node) { // rotates right on given node
        RedBlackNode<T> pivot = node.getLeft();
        if (pivot != null) {
            if (node.isLeftChild()) {
                node.getParent().setLeft(pivot);
            }
            else if (node.isRightChild()) {
                node.getParent().setRight(pivot);
            }
            pivot.setParent(node.getParent());

            node.setLeft(pivot.getRight());
            if (pivot.getRight() != null) {
                pivot.getRight().setParent(node);
            }
            pivot.setRight(node);
            node.setParent(pivot);
        }
    }


    public RedBlackNode<T> find(T data) {
        return (RedBlackNode<T>) super.find(data);
    }

    public RedBlackNode<T> getRoot() {
        return (RedBlackNode<T>) super.getRoot();
    }
}
