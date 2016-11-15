from resources.python.ds import DisjointSet


# Assumed graph is a structure in the form graph[{a: x b: y c: z...}, {a: m, b: n, d: o, e: p}...] where graph[i]
# represents the set of all vertices that vertex i is connected to and graph[i][j] represents the weight of the edge
# from i to j
def kruskal(graph):
    forest = DisjointSet(len(graph))
    ids = set()
    edges = sorted([(i, j, graph[i][j]) for j in graph for i in graph if i != j], key=lambda x: x[2], reverse=True)
    weight = 0
    while len(edges) > 0:
        a, b, w = edges.pop()
        if forest.find(a) != forest.find(b):
            forest.union(a, b)
            weight += w
            ids.add((a, b))
    return ids, weight