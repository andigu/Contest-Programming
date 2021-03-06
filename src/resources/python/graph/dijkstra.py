# TODO Implement backtrack for finding paths

import sys

from resources.python.ds.priority_queue import *


def dijkstra(graph, start=0, n=None):
    if n is None:
        n = len(graph)
    dist = [infinity] * n
    dist[start] = 0
    queue = PriorityQueue([[dist[i], i] for i in range(vertices)])
    while not queue.is_empty:
        current = queue.pop()[1]
        for neighbour in graph[current]:
            temp = dist[current] + graph[current][neighbour]
            if temp < dist[neighbour]:
                dist[neighbour] = temp
                queue.increase_priority(neighbour, temp)
    return dist

if __name__ == "__main__":
    input = sys.stdin.readline
    vertices, edges = [int(i) for i in input().split()]
    graph = [{} for i in range(vertices)]
    infinity = float("inf")
    for i in range(edges):
        a, b, weight = [int(i) for i in input().split()]
        graph[a - 1][b - 1] = min(graph[a - 1].get(b - 1, infinity), weight)
        graph[b - 1][a - 1] = min(graph[b - 1].get(a - 1, infinity), weight)
    print("\n".join([str(i) if i != infinity else "-1" for i in dijkstra(graph)]))
