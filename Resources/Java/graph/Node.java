package graph;

import java.util.Set;

/**
 * @author Andi Gu
 */
public interface Node<T> {
    T getId();

    boolean isVisited();

    Set<Node<T>> getNeighbours();


}
