import sys


# Brief description:
# For each node, find the distance farthest child from it, and the number of those children


def dfs(node):
    global ans, num
    visited[node] = True
    for child in graph[node]:
        if not visited[child]:
            dfs(child)
            temp = dp[child] + 1
            if temp + dp[node] > ans:
                ans = temp + dp[node]
                num = cnt[child] * cnt[node]
            elif temp + dp[node] == ans:
                num += cnt[child] * cnt[node]

            if temp > dp[node]:
                dp[node] = temp
                cnt[node] = cnt[child]
            elif temp == dp[node]:
                cnt[node] += cnt[child]


input = sys.stdin.readline
n = int(input())
ans, num = 0, 0
graph = [set() for i in range(n)]
for i in range(n - 1):
    a, b = [int(i) - 1 for i in input().split()]
    graph[a].add(b)
    graph[b].add(a)
visited = [False] * n
dp = [0] * n
cnt = [1] * n
dfs(0)
print(ans + 1, num)
