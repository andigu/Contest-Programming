import sys


def dfs(current, depth, array):
    array[current] = depth
    depth += 1
    for node in graph[current]:
        if array[node] == -1:
            dfs(node, depth, array)


input = sys.stdin.readline
n = int(input())
graph = [set() for i in range(n)]
for i in range(n - 1):
    a, b = [int(i) - 1 for i in input().split()]
    graph[a].add(b)
    graph[b].add(a)
depthA = [-1] * n
dfs(0, 0, depthA)
furthest = depthA.index(max(depthA))
depthB = [-1] * n
dfs(furthest, 0, depthB)
largest = max(depthB)
print(largest + 1, depthB.count(largest) * depthA.count(max(depthA)))
