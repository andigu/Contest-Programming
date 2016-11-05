package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Andi Gu
 */
public class sssp {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Graph<Integer> graph = new Graph<>();
            String[] tokens;
            tokens = reader.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]), m = Integer.parseInt(tokens[1]);
            for (int i = 0; i < n; i++) {
                graph.addVertex(i);
            }
            for (int i = 0; i < m; i++) {
                tokens = reader.readLine().split(" ");
                graph.addEdge(Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]) - 1, Integer.parseInt(tokens[2]));
            }
            Dijkstra<Integer> dijkstra = new Dijkstra<>(graph);
            Map<Integer, Double> result = dijkstra.shortestDist(0);
            double[] ans = new double[n];
            for (Integer key : result.keySet()) {
                ans[key] = result.get(key) == Double.POSITIVE_INFINITY ? -1 : result.get(key);
            }
            for (double i : ans) {
                System.out.println((int) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Dijkstra<T> {
    private Graph<T> graph;

    public Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    public Map<T, Double> shortestDist(T startId) {
        PriorityQueue<Double, Vertex<T>> queue = new PriorityQueue<>(Double::compareTo);
        Map<T, Double> distance = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        for (Vertex<T> vertex : graph.getVertices()) {
            queue.push(new PriorityPair<>(Double.POSITIVE_INFINITY, vertex));
            distance.put(vertex.getId(), Double.POSITIVE_INFINITY);
        }
        queue.changePriority(graph.getVertex(startId), 0d);
        distance.put(startId, 0d);
        Vertex<T> current;
        while (!queue.isEmpty()) {
            current = queue.pop().getValue();
            visited.add(current);
            for (Vertex<T> neighbour : current.getNeighbours()) {
                if (!visited.contains(neighbour)) {
                    double tentative = distance.get(current.getId()) + current.getWeight(neighbour);
                    if (tentative < distance.get(neighbour.getId())) {
                        queue.changePriority(neighbour, tentative);
                        distance.put(neighbour.getId(), tentative);
                    }
                }
            }
        }
        return distance;
    }
}

class PriorityPair<K, T> {
    private K priority;
    private T value;

    public PriorityPair(K priority, T value) {
        this.priority = priority;
        this.value = value;
    }

    public K getPriority() {
        return priority;
    }

    public T getValue() {
        return value;
    }

    public void setPriority(K priority) {
        this.priority = priority;
    }
}

class PriorityQueue<P, T> extends BinaryHeap<PriorityPair<P, T>> {
    private Map<T, Integer> index;
    private Comparator<P> priorityComparator;

    public PriorityQueue(Comparator<P> priorityComparator) {
        super((o1, o2) -> priorityComparator.compare(o1.getPriority(), o2.getPriority()));
        this.priorityComparator = priorityComparator;
        index = new HashMap<>();
    }

    @Override
    public void push(PriorityPair<P, T> value) {
        super.push(value);
        index.put(value.getValue(), size);
    }

    @Override
    public PriorityPair<P, T> pop() {
        index.put(data.get(size).getValue(), 1);
        PriorityPair<P, T> popped = super.pop();
        index.remove(popped.getValue());
        return popped;
    }

    @Override
    protected void swap(int indexA, int indexB) {
        index.put(data.get(indexA).getValue(), indexB);
        index.put(data.get(indexB).getValue(), indexA);
        PriorityPair<P, T> temp = data.get(indexA);
        data.set(indexA, data.get(indexB));
        data.set(indexB, temp);
    }

    public void changePriority(T element, P newPriority) {
        int index = this.index.get(element);
        int compareResult = priorityComparator.compare(newPriority, data.get(index).getPriority());
        data.get(index).setPriority(newPriority);
        if (compareResult < 0) {
            shiftUp(index);
        } else if (compareResult > 0) {
            shiftDown(index);
        }
    }
}

class Graph<T> {
    private Map<T, Vertex<T>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public void addVertex(T id) {
        if (!vertices.containsKey(id)) {
            vertices.put(id, new Vertex<>(id));
        }
    }

    public void removeVertex(T id) {
        vertices.remove(id);
    }

    public void addEdge(T vertexA, T vertexB, int weight) {
        addEdge(vertexA, vertexB, weight, true);
    }

    public void addEdge(T vertexA, T vertexB, int weight, boolean bidirectional) {
        addVertex(vertexA);
        addVertex(vertexB);
        if (vertices.get(vertexA).hasNeighbour(vertices.get(vertexB))) {
            weight = Math.min(vertices.get(vertexA).getWeight(vertices.get(vertexB)), weight);
        }
        vertices.get(vertexA).addNeighbour(vertices.get(vertexB), weight);
        if (bidirectional) {
            vertices.get(vertexB).addNeighbour(vertices.get(vertexA), weight);
        }
    }

    public void addEdge(T vertexA, T vertexB, boolean bidirectional) {
        addEdge(vertexA, vertexB, 1, bidirectional);
    }

    public void removeEdge(T vertexA, T vertexB) {
        if (vertices.get(vertexA).hasNeighbour(vertices.get(vertexB))) {
            vertices.get(vertexA).removeNeighbour(vertices.get(vertexB));
        }
    }

    public int getWeight(T vertexA, T vertexB) {
        return vertices.get(vertexA).getWeight(vertices.get(vertexB));
    }

    public Set<T> getIds() {
        return vertices.keySet();
    }

    public Vertex<T> getVertex(T id) {
        return vertices.get(id);
    }

    public String toString() {
        String result = "";
        for (Vertex<T> vertex : vertices.values()) {
            result += vertex.toString() + "\n";
        }
        return result;
    }

    public Collection<Vertex<T>> getVertices() {
        return vertices.values();
    }

    public int getSize() {
        return vertices.size();
    }
}

class Vertex<T> {
    private T id;
    private Map<Vertex<T>, Integer> edges;

    Vertex(T id) {
        this.id = id;
        edges = new HashMap<>();
    }

    public T getId() {
        return id;
    }

    public void addNeighbour(Vertex<T> vertex) {
        edges.put(vertex, 1);
    }

    void addNeighbour(Vertex<T> vertex, int weight) {
        edges.put(vertex, weight);
    }

    void removeNeighbour(Vertex<T> vertex) {
        edges.remove(vertex);
    }

    boolean hasNeighbour(Vertex<T> vertex) {
        return edges.containsKey(vertex);
    }

    public Set<Vertex<T>> getNeighbours() {
        return edges.keySet();
    }

    public int getWeight(Vertex<T> neighbour) {
        return hasNeighbour(neighbour) ? edges.get(neighbour) : -1;
    }

    public String toString() {
        String result = "ID: " + id + ", Neighbours {";
        if (getNeighbours().size() == 0) {
            result += "}";
            return result;
        } else {
            for (Vertex<T> vertex : getNeighbours()) {
                result += vertex.getId() + ": " + getWeight(vertex) + ", ";
            }
            result = result.substring(0, result.length() - 2);
            result += "}";
            return result;
        }
    }
}

class BinaryHeap<E> {
    protected List<E> data;
    private Comparator<E> comparator; // Comparator that returns positive if o2 > o1 gives a max heap (returns o2-o1)
    protected int size;

    public BinaryHeap(Comparator<E> comparator) {
        data = new ArrayList<>();
        data.add(null);
        this.comparator = comparator;
        size = 0;
    }

    public void push(E value) {
        data.add(value);
        shiftUp(++size);
    }

    public E pop() {
        E result = data.get(1);
        data.set(1, data.get(size));
        data.remove(size--);
        shiftDown(1);
        return result;
    }

    public E peek() {
        return data.get(1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected void shiftUp(int index) {
        while (index / 2 > 0 && comparator.compare(data.get(index), data.get(index / 2)) < 0) {
            swap(index, index / 2);
            index /= 2;
        }
    }

    protected void shiftDown(int index) {
        int child = index * 2 + 1 > size || compare(index * 2, index * 2 + 1) <= 0 ? index * 2 : index * 2 + 1;
        while (index * 2 <= size && compare(index, child) > 0) {
            swap(index, child);
            index = child;
            child = index * 2 + 1 > size || compare(index * 2, index * 2 + 1) <= 0 ? index * 2 : index * 2 + 1;
        }
    }

    protected void swap(int indexA, int indexB) {
        E temp = data.get(indexA);
        data.set(indexA, data.get(indexB));
        data.set(indexB, temp);
    }

    public int getSize() {
        return size;
    }

    protected int compare(int indexA, int indexB) {
        return comparator.compare(data.get(indexA), data.get(indexB));
    }
}