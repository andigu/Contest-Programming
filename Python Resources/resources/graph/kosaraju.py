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
    visit(0)
    component = {}
    def assign(u, root):
        if u not in component:
            component[u] = root
            for v in graph[u]:
                assign(v, root)

    while len(stack) > 0:
        root = stack.pop()
        assign(root, root)

    return component