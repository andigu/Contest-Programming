import sys


def find(i):
    if parent[i] == i:
        return i
    else:
        parent[i] = find(parent[i])
        return parent[i]


input = sys.stdin.readline
v, e = [int(i) for i in input().split()]
parent = [i for i in range(v)]

edges = [[int(i) - 1 for i in input().split()] for i in range(e)]
ids = set()
edges.reverse()
visited = 0
add = ids.add
for i in range(1, e + 1):
    a, b = edges.pop()
    if find(a) != find(b):
        parent[find(a)] = parent[b]
        add(i)
        visited += 1
if visited != v - 1: # Spanning tree always has v - 1 edges
    print("Disconnected Graph")
else:
    for i in ids:
        print(i)