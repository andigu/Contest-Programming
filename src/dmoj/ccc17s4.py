def find(d, x):
    return x if d[x] == x else find(d, d[x])


n, m, d = [int(i) for i in input().split(' ')]

edges = []
for i in range(m):
    a, b, w = [int(i) for i in input().split(" ")]
    edges.append([a - 1, b - 1, w, i < n - 1])
edges = sorted(edges, key=lambda x: x[2])
disjoint = [i for i in range(n)]
rank = [0] * n
total_cost = 0

to_activate = 0
to_deactivate = 0
for edge in edges:
    a, b, w, in_original = edge
    root_a, root_b = find(disjoint, a), find(disjoint, b)
    if root_a != root_b:
        if not in_original:
            to_activate += 1
        if rank[root_a] > rank[root_b]:
            disjoint[root_b] = root_a
        else:
            disjoint[root_a] = root_b
            rank[root_b] += 1 if rank[root_b] == rank[root_a] else 0
        total_cost += w
    else:
        if in_original:
            to_deactivate += 1

print(max(to_activate, to_deactivate))
