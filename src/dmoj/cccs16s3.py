def should_delete(neighbours, current, parent=-1):
    if len(neighbours[current]) > 1:
        return all(should_delete(neighbours, nbr, current) for nbr in neighbours[current] if nbr != parent)
    else:
        return current not in pho


n, m = [int(i) for i in input().split(' ')]
pho = {int(i) for i in input().split(' ')}
graph = [set() for _ in range(n)]
for i in range(n - 1):
    a, b = [int(i) for i in input().split(' ')]
    graph[a].add(b)
    graph[b].add(a)
print(graph)
print(should_delete(graph, 1, 0))
