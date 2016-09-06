Dijkstra's Algorithm
====================
Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph. Its procedure is as 
follows:
1. Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other 
nodes.
2. Set the initial node as current. Mark all other nodes unvisited. Create a set of all the unvisited nodes called the 
unvisited set.
3. For the current node, consider all of its unvisited neighbors and calculate their tentative distances. Compare the 
newly calculated tentative distance to the current assigned value and assign the smaller one. For example, if the 
current node A is marked with a distance of 6, and the edge connecting it with a neighbor B has length 2, then the 
distance to B (through A) will be 6 + 2 = 8. If B was previously marked with a distance greater than 8 then change it 
to 8. Otherwise, keep the current value.
4. When we are done considering all of the neighbors of the current node, mark the current node as visited and remove 
it from the unvisited set. A visited node will never be checked again.
5. If the destination node has been marked visited (when planning a route between two specific nodes) or if the 
smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal; 
occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. The algorithm 
has finished.
6. Otherwise, select the unvisited node that is marked with the smallest tentative distance, set it as the new
"current node", and go back to step 3.

```
function Dijkstra(Graph, source):
    create vertex set Q
    for each vertex v in Graph:             // Initialization
        dist[v] ← INFINITY                  // Unknown distance from source to v
        prev[v] ← UNDEFINED                 // Previous node in optimal path from source
        add v to Q                          // All nodes initially in Q (unvisited nodes)
    dist[source] ← 0                        // Distance from source to source
    while Q is not empty:
        u ← vertex in Q with min dist[u]    // Source node will be selected first
        remove u from Q 
        for each neighbor v of u:           // where v is still in Q.
            alt ← dist[u] + length(u, v)
            if alt < dist[v]:               // A shorter path to v has been found
                dist[v] ← alt 
                prev[v] ← u 
    return dist[], prev[]
```

Kruskal's Algorithm
===================
Kruskal's algorithm is a minimum-spanning-tree algorithm which finds an edge of the least possible weight that connects
any two trees in the forest. It is a greedy algorithm in graph theory as it finds a minimum spanning tree for a 
connected weighted graph adding increasing cost arcs at each step. This means it finds a subset of the edges that 
forms a tree that includes every vertex, where the total weight of all the edges in the tree is minimized. If the graph
is not connected, then it finds a minimum spanning forest (a minimum spanning tree for each connected component).
1. Create a forest `F` (a set of trees), where each vertex in the graph is a separate tree
2. Create a set `S` containing all the edges in the graph
3. While `S` is nonempty and F is not yet spanning
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
    
Prim's Algorithm
================
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