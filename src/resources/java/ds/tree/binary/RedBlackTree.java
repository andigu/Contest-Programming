package resources.java.ds.tree.binary;

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
     * <ol>
     * <li> N is the root node, i.e., first node of red–black tree </li>
     * <li> N's parent (P) is black </li>
     * <li> N's parent (P) and uncle (U) are red </li>
     * <li>N is added to right of left child of grandparent, or N is added to left of right child of grandparent
     * (P is red and U is black) </li>
     * <li> N is added to left of left child of grandparent, or N is added to right of right child of grandparent
     * (P is red and U is black) </li>
     * </ol>
     *
     * @param node The node that was inserted
     */
    private void insertAdjust(RedBlackNode<T> node) {
        if (node.getParent() != null && node.getParent().isRed()) { // If the parent is black the tree is still valid
            RedBlackNode<T> uncle = node.getParent().getSibling();
            RedBlackNode<T> grandparent;
            if (uncle != null && uncle.isRed()) { // Case 3
                grandparent = node.getGrandparent();
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
                grandparent = node.getGrandparent();
                node.getParent().makeBlack();
                grandparent.makeRed();
                if (node.isLeftChild()) {
                    rotateRight(grandparent);
                } else {
                    rotateLeft(grandparent);
                }
            }
        }
        getRoot().makeBlack();
    }

    public void delete(T data) {
        RedBlackNode<T> node = find(data);
        if (node != null) {
            if (node.getLeft() != null && node.getRight() != null) {
                RedBlackNode<T> successor = node.getSuccessor();
                node.setData(successor.getData());
                node = successor;
            }
            RedBlackNode<T> leafChild = node.getLeft() == null ? node.getRight() : node.getLeft();
            if (node.isBlack() && leafChild != null) {
                if (leafChild.isBlack()) {
                    deleteAdjust(leafChild);
                } else {
                    leafChild.makeBlack();
                }
            }
            replaceNodeInParent(node, leafChild);
        }
    }

    private void deleteAdjust(RedBlackNode<T> node) {
        if (node.getParent() != null) {
            RedBlackNode<T> sibling = node.getSibling();
            if (sibling != null && sibling.isRed()) {
                node.getParent().makeRed();
                sibling.makeBlack();
                if (node.isLeftChild()) {
                    rotateLeft(node.getParent());
                } else rotateRight(node.getParent());
            }

            if (node.getParent().isBlack() && siblingCheck(sibling)) {
                if (sibling != null) {
                    sibling.makeRed();
                }
                deleteAdjust(node.getParent());
            } else {
                if (node.getParent().isRed() && siblingCheck(sibling)) {
                    if (sibling != null) {
                        sibling.makeRed();
                    }
                    node.getParent().makeBlack();
                } else {
                    if (sibling != null && sibling.isBlack()) {
                        if (node.isLeftChild() && sibling.getRight().isBlack() && sibling.getLeft().isRed()) {
                            sibling.makeRed();
                            sibling.getLeft().makeBlack();
                            rotateRight(sibling);
                        } else if (node.isRightChild() && sibling.getLeft().isBlack() && sibling.getRight().isRed()) {
                            sibling.makeRed();
                            sibling.getRight().makeBlack();
                            rotateLeft(sibling);
                        }
                    }
                    if (sibling != null) {
                        sibling.copyColor(node.getParent());
                    }
                    if (node.isLeftChild()) {
                        if (sibling != null) {
                            sibling.getRight().makeBlack();
                        }
                        rotateLeft(node.getParent());
                    } else {
                        if (sibling != null) {
                            sibling.getLeft().makeBlack();
                        }
                        rotateRight(node.getParent());
                    }
                }
            }
        }
    }


    private boolean siblingCheck(RedBlackNode<T> sibling) { // Used twice in a certain case for deletion
        return sibling == null || sibling.isBlack() && sibling.getLeft().isBlack() && sibling.getRight().isBlack();
    }

    private void rotateLeft(RedBlackNode<T> node) {
        RedBlackNode<T> pivot = node.getRight();
        if (node == getRoot()) {
            setRoot(pivot);
        }
        if (pivot != null) {
            switchParent(node, pivot);
            node.setRight(pivot.getLeft());
            if (pivot.getLeft() != null) {
                pivot.getLeft().setParent(node);
            }
            pivot.setLeft(node);
        }
    }

    private void rotateRight(RedBlackNode<T> node) {
        RedBlackNode<T> pivot = node.getLeft();
        if (node == getRoot()) {
            setRoot(pivot);
        }
        if (pivot != null) {
            switchParent(node, pivot);
            node.setLeft(pivot.getRight());
            if (pivot.getRight() != null) {
                pivot.getRight().setParent(node);
            }
            pivot.setRight(node);
            node.setParent(pivot);
        }
    }

    private void switchParent(RedBlackNode<T> A, RedBlackNode<T> B) {
        B.setParent(A.getParent());
        if (A.isLeftChild()) {
            A.getParent().setLeft(B);
        } else if (A.isRightChild()) {
            A.getParent().setRight(B);
        }
        A.setParent(B);
    }

    public RedBlackNode<T> find(T data) {
        return (RedBlackNode<T>) super.find(data);
    }

    public RedBlackNode<T> getRoot() {
        return (RedBlackNode<T>) super.getRoot();
    }
}
