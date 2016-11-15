def strongly_connected_component(graph):
    n = len(graph)
    visited = [False] * n
    stack = []

    def visit(u):
        if not visited[u]:
            visited[u] = True
            for v in graph[u]:
                visit(v)
            stack.insert(0, u)

    for i in range(n):
        if not visited[i]:
            visit(i)
    component = {}

    reversed_graph = [set() for _ in range(n)]
    for i in range(n):
        for v in graph[i]:
            reversed_graph[v].add(i)
    def assign(u, root):
        if not visited[u]:
            visited[u] = True
            component[u] = root
            for v in reversed_graph[u]:
                assign(v, root)

    while len(stack) > 0:
        root = stack.pop()
        assign(root, root)

    return component


n, k = [int(i) for i in input().split()]
graph = [set() for i in range(n)]
for i in range(k):
    a, b, x, y = [int(i) for i in input().split()]
    a -= 1
    b -= 1
    if x > y:
        graph[a].add(b)
    elif y > x:
        graph[b].add(a)

components = strongly_connected_component(graph)
x = {}
for node in components:
    if components[node] not in x:
        x[components[node]] = {node}
    else:
        x[components[node]].add(node)
n = 0
print(x)
for i in x:
    a = len(x[i])
    if a > 2:
        n += a
print(n)
