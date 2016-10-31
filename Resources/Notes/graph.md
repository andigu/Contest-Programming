# Graphs #

A graph data structure consists of a set of vertices or nodes, together with a set of unordered pairs of these vertices 
for an undirected graph or a set of ordered pairs for a directed graph. A graph data structure may also associate to 
each edge some weight.
The basic operations provided by a graph data structure usually include:

* `adjacent(x, y)`: tests whether there is an edge from the vertices `x` to `y`
* `neighbors(x)`: lists all vertices `y` such that there is an edge from the vertices `x` to `y`
* `add_vertex(x)`: adds the vertex `x`, if it is not there
* `remove_vertex(x)`: removes the vertex `x`, if it is there
* `add_edge(x, y)`: adds the edge from the vertices `x` to `y`, if it is not there
* `remove_edge(x, y)`: removes the edge from the vertices `x` to `y`, if it is there
* `get_vertex_value(x)`: returns the value associated with the vertex `x`
* `set_vertex_value(x, v)`: sets the value associated with the vertex `x` to `v`

Structures that associate weights to the edges usually also provide:

* `get_edge_value(x, y)`: returns the value associated with the edge `(x, y)`
* `set_edge_value(x, y, v)`: sets the value associated with the edge `(x, y)` to `v`

## Topological Sort ##

Topological sorts are used to find what order to traverse a graph in order to ensure each vertex's parent is visited
before the vertex itself is visited. To do a topological sort on a graph, store the order of nodes in an ordered list `A`:
1. Find a vertex `x` with no dependencies
2. Add it to the array `A`
3. Remove `x` and all edges that start from that vertex
4. Repeat until the graph has no nodes

## Dijkstra's Algorithm ##

Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph. Its procedure is as 
follows:

1. Assign to every vertex a tentative distance value: set it to zero for our initial vertex and to infinity for all other 
nodes.
2. Set the initial vertex as current. Mark all other nodes unvisited. Create a set of all the unvisited nodes called the 
unvisited set.
3. For the current vertex, consider all of its unvisited neighbors and calculate their tentative distances. Compare the 
newly calculated tentative distance to the current assigned value and assign the smaller one. For example, if the 
current vertex `A` is marked with a distance of 6, and the edge connecting it with a neighbor `B` has length `2`, then 
the distance to `B` (through `A`) will be `6 + 2 = 8`. If `B` was previously marked with a distance greater than `8` then 
change it to `8`. Otherwise, keep the current value.
4. When we are done considering all of the neighbors of the current vertex, mark the current vertex as visited and remove 
it from the unvisited set. A visited vertex will never be checked again.
5. If the destination vertex has been marked visited (when planning a route between two specific nodes) or if the 
smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal; 
occurs when there is no connection between the initial vertex and remaining unvisited nodes), then stop. The algorithm 
has finished.
6. Otherwise, select the unvisited vertex that is marked with the smallest tentative distance, set it as the new
"current vertex", and go back to step 3.

```
function Dijkstra(Graph, source):
    create vertex set Q
    for each vertex v in Graph:             // Initialization
        dist[v] ← INFINITY                  // Unknown distance from source to v
        prev[v] ← UNDEFINED                 // Previous vertex in optimal path from source
        add v to Q                          // All nodes initially in Q (unvisited nodes)
    dist[source] ← 0                        // Distance from source to source
    while Q is not empty:
        u ← vertex in Q with min dist[u]    // Source vertex will be selected first
        remove u from Q 
        for each neighbor v of u:           // where v is still in Q.
            alt ← dist[u] + length(u, v)
            if alt < dist[v]:               // A shorter path to v has been found
                dist[v] ← alt 
                prev[v] ← u 
    return dist[], prev[]
```

# Trees #

A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any acyclic 
connected graph is a tree. A forest is a disjoint union of trees.
If a graph has `n` vertices, then the following conditions must be satisfied for it to be considered a tree:

* It is is connected and has `n − 1` edges
* It has no simple cycles

## Forests ##

A forest is an undirected graph, all of whose connected components are trees; in other words, the graph consists of a 
disjoint union of trees. Equivalently, a forest is an undirected acyclic graph.

## Minimum Spanning Trees ##

A minimum spanning tree of a graph `G` is a tree that includes all the vertices of `G`, with minimal total weighting
for its edges. Some characteristics of a minimum spanning tree include:

* If there are `n` vertices in the graph, the minimum spanning tree contains `n - 1` edges
* If every edges has a distinct weight, there will only be one possible minimum spanning tree
* If the minimum cost edge e of a graph is unique, then this edge is included in all the minimum spanning tree

There are several ways to find the minimum spanning tree of a graph. Two popular ones are Kruskal's algorithm (good 
for the typical use case, used with disjoint sets typically), and Prim's algorithm (good for graphs with a high number
of edges).

### Kruskal's Algorithm ###

Kruskal's algorithm is a minimum-spanning-tree algorithm which finds an edge of the least possible weight that connects
any two trees in the forest. It is a greedy algorithm in graph theory as it finds a minimum spanning tree for a 
connected weighted graph adding increasing cost arcs at each step. This means it finds a subset of the edges that 
forms a tree that includes every vertex, where the total weight of all the edges in the tree is minimized. If the graph
is not connected, then it finds a minimum spanning forest (a minimum spanning tree for each connected component).

1. Create a forest `F` (a set of trees), where each vertex in the graph is a separate tree
2. Create a set `S` containing all the edges in the graph
3. While `S` is nonempty and `F` is not yet spanning
    1. Remove an edge with minimum weight from `S`
    2. If the removed edge connects two different trees then add it to the forest `F`, combining two trees into a 
    single tree
```
A = ∅
foreach v ∈ vertices:
    MAKE-SET(v)
foreach (u, v) in edges ordered by weight(u, v), increasing:
    if FIND-SET(u) ≠ FIND-SET(v):
        A = A ∪ {(u, v)}
        UNION(u, v)
return A
```
    
### Prim's Algorithm ###

Prim's algorithm is a greedy algorithm that finds a minimum spanning tree for a weighted undirected graph. This means
it finds a subset of the edges that forms a tree that includes every vertex, where the total weight of all the edges in
the tree is minimized. The algorithm operates by building this tree one vertex at a time, from an arbitrary starting 
vertex, at each step adding the cheapest possible connection from the tree to another vertex.

1. Associate with each vertex `v` of the graph a number `C[v]` (the cheapest cost of a connection to `v`) and an edge 
`E[v]` (the edge providing that cheapest connection). To initialize these values, set all values of `C[v]` to `∞` and 
set each `E[v]` to a special flag value indicating that there is no edge connecting `v` to earlier vertices.
2. Initialize an empty forest `F` and a set `Q` of vertices that have not yet been included in `F` (initially, all 
vertices)
3. Repeat the following steps until `Q` is empty:
    1. Find and remove a vertex `v` from `Q` having the minimum possible value of `C[v]`
    2. Add `v` to `F` and, if `E[v]` is not the special flag value, also add `E[v]` to `F`
    3. Loop over the edges `vw` connecting `v` to other vertices `w`. For each such edge, if `w` still belongs to `Q` 
    and `vw` has smaller weight than `C[w]`, perform the following steps:
        1. Set `C[w]` to the cost of edge `vw`
        2. Set `E[w]` to point to edge `vw`
4. Return `F`

## Strongly Connected Components ##

Strongly connected components are defined as subgraphs of a graph `G` such that there is a path between every vertex
in that subgraph. Kosaraju's algorithm and Tarjan's Algorithm both have a an asmyptopic complexity of `O(V+E)`.

### Kosaraju's Algorithm ###
A popular algorithm for finding the strongly connected components in a graph is Kosaraju's algorithm.
It runs in `O(V+E)` time, and the method is as follows:

1. Create an empty stack `S` and do DFS traversal of a graph. In DFS traversal, after calling recursive DFS for adjacent
vertices of a vertex, push the vertex to stack.
2. Reverse directions of all arcs to obtain the transpose graph.
3. One by one pop a vertex from `S` while `S` is not empty. Let the popped vertex be `v`. Take `v` as source and do DFS.
The DFS starting from `v` prints strongly connected component of `v`.

### Tarjan's Algorithm ###
* The crux of the algorithm comes in determining whether a node is the root of a strongly connected component.
* The root node is the first node of the strongly connected component which is encountered during the depth first 
traversal
* To find the root, each node is given a depth search index `v.index` which numbers the nodes consecutively in the order
in which they are discovered. In addition, each node is assigned a value of `v.lowlink` that is equal to the index 
of some node reachable from `v` and always less than `v.index`, or equal to `v.index` if no other node is reachable 
from `v`
Pseudocode:
```
tarjan(u) {
    DFN[u] = Low[u] = ++Index
    Stack.push(u)
    for each (u, v) in E
        if (v is not visited)
            tarjan(v)
            Low[u] = min(Low[u], Low[v])
        else if (v in Stack)
            Low[u] = min(Low[u], DFN[v])
    if (DFN[u] == Low[u])
        repeat
        v = Stack.pop
        print v until (u == v)
```
           
 