package resources.java.ds.tree.binary;

/**
 * @author Andi Gu
 */
public class RedBlackNode<E extends Comparable<E>> extends Node<E> {
    private boolean color; // true for black

    public RedBlackNode(E data) {
        super(data);
        color = true;
    }

    public RedBlackNode<E> getParent() {
        return (RedBlackNode<E>) super.getParent();
    }

    public RedBlackNode<E> getRight() {
        return (RedBlackNode<E>) super.getRight();
    }

    public RedBlackNode<E> getLeft() {
        return (RedBlackNode<E>) super.getLeft();
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

    public RedBlackNode<E> getSibling() {
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

    public RedBlackNode<E> getGrandparent() {
        if (getParent() == null) {
            return null;
        }
        else {
            return getParent().getParent();
        }
    }

    public RedBlackNode<E> getSuccessor() {
        return (RedBlackNode<E>) super.getSuccessor();
    }

    public void copyColor(RedBlackNode<E> toCopy) {
        if (toCopy == null || toCopy.isBlack()) {
            makeBlack();
        }
        else {
            makeRed();
        }
    }
}
