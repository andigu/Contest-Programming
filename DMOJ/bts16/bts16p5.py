import sys


def get_info(node):
    if info[node] is None:
        value = values[node]
        edges = parent_weight[node]
        for neighbour in graph[node]:
            a, b = get_info(neighbour)
            edges += b
            value += a
        info[node] = [value, edges]
    return info[node]


input = sys.stdin.readline
n, wanted, max_weight = [int(i) for i in input().split()]
values = [int(i) for i in input().split()]
graph = [set() for i in range(n)]
parent_weight = [-1] * n
for i in range(n - 1):
    a, b, weight = [int(i) for i in input().split()]
    graph[a - 1].add(b - 1)
    parent_weight[b - 1] = weight
count = 0
info = [None] * n
for i in range(1, n):
    a, b = get_info(i)
    if a >= wanted and b <= max_weight:
        count += 1
print(count)