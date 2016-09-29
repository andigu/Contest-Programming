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
maxA = max(depthA)
furthest = depthA.index(maxA)
depthB = [-1] * n
dfs(furthest, 0, depthB)
maxB = max(depthB)
count = 0
for i in range(n):
    if depthA[i] == maxA and depthB[i] == maxB:
        count += 1
num = (depthB.count(maxB)) * (depthA.count(max(depthA)) - count)
print(str(maxB + 1) + " " + str(num))