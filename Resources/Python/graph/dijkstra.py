from ds.priority_queue import PriorityQueue

def dijkstra(graph, start=0, n=None):
    if n is None:
        n = len(graph)
    dist = [float("inf")] * n
    dist[start] = 0
    unvisited = {i for i in range(n)}  # inefficient!
    queue = PriorityQueue([(dist[i], i) for i in unvisited])

    while not queue.is_empty:
        current = queue.pop()
        unvisited.remove(current)
        for neighbour in graph[current]:
            temp = dist[current] + graph[current][neighbour]
            if temp < dist[neighbour]:
                dist[neighbour] = temp
        current = PriorityQueue([(dist[i], i) for i in unvisited])

    return dist[-1]

