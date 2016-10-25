from resource.graph.scc import strongly_connected_component

def best(node, visited):
    if node == n - 1:
        return loot[node]
    else:
        if not visited[node] and not left[province[node]]:
            visited[node] = True
            largest = 0
            for neighbour in graph[node]:
                if province[neighbour] != province[node]:
                    left[province[neighbour]] = True
                temp = best(neighbour, visited)
                if temp > largest:
                    largest = temp
                left[province[neighbour]] = False
            visited[node] = False
            return largest + loot[node]
        return 0



n, m = [int(i) for i in input().split()]
loot = [int(i) for i in input().split()]
graph = [set() for i in range(n)]
for i in range(m):
    a, b = [int(i) - 1 for i in input().split()]
    if a != b:
        graph[a].add(b)
province = [-1] * n
provinces = strongly_connected_component(graph)
left = [False] * len(provinces)
for i in range(len(provinces)):
    for node in provinces[i]:
        province[node] = i

print(best(0, [False] * n))