package ds.tree.binary;

/**
 * @author Andi Gu
 */
public class RedBlackNode<T extends Comparable<T>> extends Node<T> {
    private boolean color; // true for black

    public RedBlackNode(T data) {
        super(data);
        color = true;
    }

    public RedBlackNode<T> getParent() {
        return (RedBlackNode<T>) super.getParent();
    }

    public RedBlackNode<T> getRight() {
        return (RedBlackNode<T>) super.getRight();
    }

    public RedBlackNode<T> getLeft() {
        return (RedBlackNode<T>) super.getLeft();
    }

    public boolean isBlack() {
        return color;
    }

    public boolean isRed() {
        return !color;
    }

    public void makeBlack() {
        color = true;
    }

    public void makeRed() {
        color = false;
    }

    public RedBlackNode<T> getSibling() {
        if (getParent() == null) {
            return null;
        }
        else if (isLeftChild()) {
            return getParent().getRight();
        }
        else {
            return getParent().getLeft();
        }
    }

    public RedBlackNode<T> getGrandparent() {
        if (getParent() == null) {
            return null;
        }
        else {
            return getParent().getParent();
        }
    }

    public RedBlackNode<T> getSuccessor() {
        return (RedBlackNode<T>) super.getSuccessor();
    }

    public void copyColor(RedBlackNode<T> toCopy) {
        if (toCopy == null || toCopy.isBlack()) {
            makeBlack();
        }
        else {
            makeRed();
        }
    }
}
