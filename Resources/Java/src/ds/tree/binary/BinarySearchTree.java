package ds.tree.binary;

/**
 * @author Andi Gu
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree() {
        setRoot(null);
    }

    public BinarySearchTree(E[] array) {
        for (E data : array) {
            insert(data);
        }
    }

    public Node<E> getRoot() {
        return root;
    }

    public void insert(E data) {
        insert(new Node<>(data));
    }

    public void insert(Node<E> node) {
        if (root == null) {
            root = node;
        } else {
            Node<E> current = root;
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

    public Node<E> find(E data) {
        Node<E> current = root;
        while (current != null && current.getData() != data) {
            if (current.getData().compareTo(data) > 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return current;
    }

    public void delete(E data) {
        delete(find(data));
    }

    public void delete(Node<E> node) {
        if (node != null) {
            if (node.getLeft() != null && node.getRight() != null) {
                Node<E> successor = node.getSuccessor();
                node.setData(successor.getData());
                node = successor;
            }
            Node<E> leafChild = node.getLeft() == null ? node.getRight() : node.getLeft();
            replaceNodeInParent(node, leafChild);
        }
    }

    public void replaceNodeInParent(Node<E> nodeA, Node<E> nodeB) { // Assumes it is safe to replace nodeA
        if (nodeA.getParent() == null) {
            if (nodeB != null) nodeB.setParent(nodeA.getParent());
            setRoot(nodeB);
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

    private int getSize(Node<E> node) {
        int result = 1;
        if (node.getRight() != null) {
            result += getSize(node.getRight());
        }
        if (node.getLeft() != null) {
            result += getSize(node.getLeft());
        }
        return result;
    }

    public void setRoot(Node<E> node) {
        root = node;
    }
}
