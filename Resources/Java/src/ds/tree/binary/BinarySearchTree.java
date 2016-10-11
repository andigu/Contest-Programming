package ds.tree.binary;

/**
 * @author Andi Gu
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(T[] array) {
        for (T data : array) {
            insert(data);
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public void insert(T data) {
        insert(new Node<>(data));
    }

    public void insert(Node<T> node) {
        if (root == null) {
            root = node;
        } else {
            Node<T> current = root;
            boolean done = false;
            while (!done) {
                if (node.compareTo(current) < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(node);
                        done = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() == null) {
                        current.setRight(node);
                        done = true;
                    } else {
                        current = current.getRight();
                    }
                }
            }
            node.setParent(current);
        }
    }

    public Node<T> find(T data) {
        Node<T> current = root;
        while (current != null && current.getData() != data) {
            if (current.getData().compareTo(data) > 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return current;
    }

    public void delete(T data) {
        delete(find(data));
    }

    public void delete(Node<T> node) {
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

    public void replaceNodeInParent(Node<T> nodeA, Node<T> nodeB) { // Assumes it is safe to replace nodeA
        if (nodeA.getParent() == null && nodeB == null) { // Signals to delete the root
            root = null;
        }
        else {
            if (nodeA.isLeftChild()) {
                nodeA.getParent().setLeft(nodeB);
            } else if (nodeA.isRightChild()) {
                nodeA.getParent().setRight(nodeB);
            }
            if (nodeB != null) {
                nodeB.setParent(nodeA.getParent());
            }
        }
    }

    public int getSize() {
        if (root == null) {
            return 0;
        } else {
            return getSize(root);
        }
    }

    private int getSize(Node<T> node) {
        int result = 1;
        if (node.getRight() != null) {
            result += getSize(node.getRight());
        }
        if (node.getLeft() != null) {
            result += getSize(node.getLeft());
        }
        return result;
    }
}
