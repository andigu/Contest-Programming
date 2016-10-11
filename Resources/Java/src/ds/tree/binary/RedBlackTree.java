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
        super.insert(node);
        insertAdjust(node);
    }

    private void insertAdjust(RedBlackNode<T> node) {
        node.makeRed();
        if (node.getParent() != null && node.getParent().isRed()) { // Double red problem
            RedBlackNode<T> sibling = node.getParent().getSibling(); // The current node is guaranteed to have a parent
            RedBlackNode<T> grandparent = node.getGrandparent();
            if (sibling != null && sibling.isRed()) {
                node.getParent().makeBlack();
                sibling.makeBlack();
                if (grandparent != null) {
                    grandparent.makeRed();
                    insertAdjust(grandparent);
                }
            } else if (node.getParent().isLeftChild()) {
                if (node.isRightChild()) {
                    rotateRight(node = node.getParent());
                }
                node.getParent().makeBlack();
                if (grandparent != null) {
                    grandparent.makeRed();
                    rotateLeft(grandparent);
                }
            } else if (node.getParent().isRightChild()) {
                if (node.isLeftChild()) {
                    rotateRight(node = node.getParent());
                }
                node.getParent().makeBlack();
                if (grandparent != null) {
                    grandparent.makeRed();
                    rotateLeft(grandparent);
                }
            }
        }
        getRoot().makeBlack();
    }

    public void delete(T data) {
        delete(find(data));
    }

    public void delete(RedBlackNode<T> node) {
        if (node != null) {
            if (node.getLeft() != null && node.getRight() != null) {
                RedBlackNode<T> successor = node.getSuccessor();
                node.setData(successor.getData());
                node = successor;
            }
            if (node.getLeft() == null && node.getRight() == null) {
                replaceNodeInParent(node, null);
                if (node.isBlack()) {
                    deleteAdjust(node);
                }
            } else if (node.getLeft() == null) {
                replaceNodeInParent(node, node.getRight());
                if (node.getRight().isBlack()) {
                    deleteAdjust(node.getRight());
                }
            } else {
                replaceNodeInParent(node, node.getLeft());
                if (node.getLeft().isBlack()) {
                    deleteAdjust(node.getLeft());
                }
            }
        }
    }

    private void deleteAdjust(RedBlackNode<T> node) {
        while (node != getRoot() && node.isBlack()) {
            if (node == node.getParent().getLeft()) {
                RedBlackNode<T> sibling = node.getParent().getRight();
                if (sibling.isRed()) {
                    sibling.makeBlack();
                    node.getParent().makeRed();
                    rotateLeft(node.getParent());
                    sibling = node.getParent().getRight();
                }
                if (sibling.getLeft().isBlack() && sibling.getRight().isBlack()) {
                    sibling.makeRed();
                    node = node.getParent();
                } else {
                    if (sibling.getRight().isBlack()){
                        sibling.getLeft().makeBlack();
                        sibling.makeRed();
                        rotateRight(sibling);
                        sibling = node.getParent().getRight();
                    }
                    if (node.getParent().isBlack()) {
                        sibling.makeBlack();
                    } else {
                        sibling.makeRed();
                    }
                    node.getParent().makeBlack();
                    sibling.getRight().makeBlack();
                    rotateLeft(node.getParent());
                    node = getRoot();
                }
            } else {
                RedBlackNode<T> sibling = node.getParent().getLeft();
                if (sibling.isRed()) {
                    sibling.makeBlack();
                    node.getParent().makeRed();
                    rotateRight(node.getParent());
                    sibling = node.getParent().getLeft();
                }
                if (sibling.getLeft().isBlack() && sibling.getRight().isBlack()) {
                    sibling.makeRed();
                    node = node.getParent();
                } else {
                    if (sibling.getLeft().isBlack()) {
                        sibling.getRight().makeBlack();
                        sibling.makeRed();
                        rotateLeft(sibling);
                        sibling = node.getParent().getLeft();
                    }
                    if (node.getParent().isBlack()) {
                        sibling.makeBlack();
                    } else {
                        sibling.makeRed();
                    }
                    node.getParent().makeBlack();
                    sibling.getLeft().makeBlack();
                    rotateRight(node.getParent());
                    node = getRoot();
                }
            }
        }
        node.makeBlack();
    }

    private void rotateLeft(RedBlackNode<T> node) { // rotate left on given node
        if (node.getRight() != null) {
            RedBlackNode<T> pivot = node.getRight();
            node.setRight(pivot.getLeft());
            swap(node, pivot);
            pivot.setLeft(node);
        }
    }

    private void rotateRight(RedBlackNode<T> node) { // rotates right on given node
        if (node.getLeft() != null) {
            RedBlackNode<T> pivot = node.getLeft();
            node.setLeft(pivot.getRight());
            swap(node, pivot);
            pivot.setRight(node);
        }
    }


    private void swap(RedBlackNode<T> node, RedBlackNode<T> pivot) { // helper function for rotations: swaps two nodes with each other
        if (node.getParent() == null) {
            pivot.removeFromParent();
            setRoot(pivot);
        } else if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(pivot);
        } else {
            node.getParent().setRight(pivot);
        }
    }


    public RedBlackNode<T> find(T data) {
        return (RedBlackNode<T>) super.find(data);
    }

    public RedBlackNode<T> getRoot() {
        return (RedBlackNode<T>) super.getRoot();
    }

    public void setRoot(RedBlackNode<T> node) {
        super.setRoot(node);
    }
}
