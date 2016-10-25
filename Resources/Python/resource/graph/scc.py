def strongly_connected_component(graph):
    n = len(graph)
    stack = []
    visited = [False] * n
    dfs(graph, 0, stack, visited)

    reversed_graph = [set() for i in range(n)]
    for i in range(n):
        for node in graph[i]:
            reversed_graph[node].add(i)
    components = []
    visited = [False] * n
    while len(stack) > 0:
        component = set()
        dfs_component(reversed_graph, stack[-1], visited, component, stack)
        components.append(component)
    return components




def dfs(graph, node, stack, visited):
    if not visited[node]:
        visited[node] = True
        for neighbour in graph[node]:
            dfs(graph, neighbour, stack, visited)
        stack.append(node)

def dfs_component(graph, node, visited, component, stack):
    if not visited[node]:
        component.add(node)
        visited[node] = True
        stack.remove(node)
        for neighbour in graph[node]:
            dfs_component(graph, neighbour, visited, component, stack)

if __name__ == "__main__":
    graph = [{2, 3}, {0}, {1}, {4}, {}]
    print(strongly_connected_component(graph))