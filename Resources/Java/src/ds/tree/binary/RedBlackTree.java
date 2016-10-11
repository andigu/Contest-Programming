package ds.tree.binary;

/**
 * @author Andi Gu
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    RedBlackNode<T> root;

    public RedBlackTree() {
        root = null;
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
                    grandparent.makeBlack();
                    insertAdjust(grandparent);
                }
            }
            else if (node.getParent().isLeftChild()) {
                if (node.isRightChild()) {
                    // rotate right, set node to parent of node
                }
                node.getParent().makeBlack();
                if (grandparent != null) {
                    grandparent.makeRed();
                    // rotate left on grandparent
                }
            }
            else if (node.getParent().isRightChild()) {
                if (node.isLeftChild()) {
                    // rotate right, set node to be parent of node
                }
                node.getParent().makeBlack();
                if (grandparent != null) {
                    grandparent.makeRed();
                    // rotate left on grandparent
                }
            }
        }
        root.makeBlack();
    }

    public void delete(T data) {
        delete(find(data));
    }

    public void delete(RedBlackNode<T> node) {
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                replaceNodeInParent(node, null);
            } else if (node.getLeft() == null) {  // Has a right subtree but no left subtree
                replaceNodeInParent(node, node.getRight());
            } else if (node.getRight() == null) {
                replaceNodeInParent(node, node.getLeft());
            } else if (node.getLeft() != null && node.getRight() != null) {
                Node<T> successor = node.getSuccessor();
                delete(successor);
                node.setData(successor.getData());
            }
        }
    }

    private void deleteAdjust(RedBlackNode<T> node) {

    }

    public RedBlackNode<T> find(T data) {
        return (RedBlackNode<T>) super.find(data);
    }
}
