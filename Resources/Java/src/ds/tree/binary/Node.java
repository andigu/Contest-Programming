package ds.tree.binary;

/**
 * @author Andi Gu
 */
public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    public Node(T data) {
        this.data = data;
        parent = null;
        left = null;
        right = null;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public boolean isLeftChild() {
        return parent != null && parent.getLeft() == this;
    }

    public boolean isRightChild() {
        return parent != null && parent.getRight() == this;
    }

    public T getData() {
        return data;
    }

    public int compareTo(Node<T> node) {
        return data.compareTo(node.getData());
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getSuccessor() {
        Node<T> node = this;
        if (node.getRight() != null) {
            node = node.getRight();
            while (node.getLeft() != null) {
                node = node.getLeft();
            }
            return node;
        } else {
            while (node.getParent() != null && node == node.getParent().getRight()) {
                node = node.getParent();
            }
            return node.getParent();
        }
    }
}
