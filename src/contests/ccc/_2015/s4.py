import sys


def solve():
    dist = [float("inf") for _ in range(v)]
    health = [0 for _ in range(v)]
    dist[x] = 0
    health[x] = thickness
    queue = {i for i in range(v)}
    while len(queue) > 0:
        best_dist = float("inf")
        current = -1
        for i in queue:
            if dist[i] < best_dist and health[i] > 0:
                current = i
                best_dist = dist[i]
        if current == -1:
            break
        else:
            queue.remove(current)

        for neighbour in graph[current]:
            for cost, damage in graph[current][neighbour]:
                temp_dist = dist[current] + cost
                temp_health = health[current] - damage
                if temp_health > 0 and temp_dist < dist[neighbour]:
                    dist[neighbour] = temp_dist
                    health[neighbour] = temp_health
    return dist[y] if dist[y] != float("inf") else -1


input = sys.stdin.readline
thickness, v, e = [int(i) for i in input().split()]
graph = [{} for i in range(v)]
for i in range(e):
    a, b, time, damage = [int(i) for i in input().split()]
    if (b - 1) in graph[a - 1]:
        graph[a - 1][b - 1].add((time, damage))
        graph[b - 1][a - 1].add((time, damage))
    else:
        graph[a - 1][b - 1] = {(time, damage)}
        graph[b - 1][a - 1] = {(time, damage)}
x, y = [int(i) - 1 for i in input().split()]
print(solve())
