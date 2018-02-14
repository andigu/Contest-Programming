from heapq import *


def dijkstra(g, src, dest):
    to_visit, visited = [(0, src)], set()
    while to_visit:
        dist, v = heappop(to_visit)
        if v not in visited:
            visited.add(v)
            if v == dest:
                return dist
            for i in range(len(g[v])):
                if g[v][i] > 0 and i not in visited:
                    heappush(to_visit, (g[v][i] + dist, i))


graph = [
    [0, 3, 5, 9],
    [3, 0, 6, -1],
    [5, 6, 0, 2],
    [9, -1, 2, 0]
]
print(dijkstra(graph, 0, 3))
