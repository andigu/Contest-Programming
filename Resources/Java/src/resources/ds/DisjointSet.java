package resources.ds;

/**
 * @author Andi Gu
 */
public class DisjointSet {
    private int[] parent; //parent
    private int[] size; //size of the set

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public void union(int a, int b) {
        if (find(a) != find(b)) {
            size[find(b)] += size[find(a)];
            parent[find(a)] = parent[b];
        }
    }

    public int getSize(int i) {
        return size[find(i)];
    }
}
