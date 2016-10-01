import sys


# Brief description:
# For each node, find the distance farthest child from it, and the number of those children


def dfs(node):
    global ans, num
    visited[node] = True
    best_len, best_count = 0, 1
    for child in graph[node]:
        if not visited[child]:
            child_len, child_count = dfs(child)
            if child_len + best_len > ans:
                ans = child_len + best_len
                num = child_count * best_count
            elif child_len + best_len == ans:
                num += child_count * best_count
            if child_len > best_len:
                best_len = child_len
                best_count = child_count
            elif child_len == best_len:
                best_count += child_count
    return best_len + 1, best_count


input = sys.stdin.readline
n = int(input())
ans, num = 0, 0
graph = [set() for i in range(n)]
for i in range(n - 1):
    a, b = [int(i) - 1 for i in input().split()]
    graph[a].add(b)
    graph[b].add(a)
visited = [False] * n
dfs(0)
print(ans + 1, num)
