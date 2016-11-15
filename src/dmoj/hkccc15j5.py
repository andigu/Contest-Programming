def dfs(node, x, color):
    if dfn[node] != -1:
        colours[-1][node] = color
        dfn[node] = x
        for neighbour in graph[node]:
            x += 1
            dfs(neighbour, x, not color)
    else:
        cycles.add(x - dfn[node])

m, n = [int(i) for i in input().split()]
graph = [set() for i in range(n)]
for i in range(m):
    a, b = [int(i) - 1 for i in input().split()]
    graph[a].add(b)
    graph[b].add(a)
dfn = [-1] * n
colours = []
cycles = set()
for i in range(n):
    colours.append({})
    if dfn[i] != -1:
        dfs(i, 0, False)
