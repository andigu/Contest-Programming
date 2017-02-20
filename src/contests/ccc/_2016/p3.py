import sys


def dfs(node, parent=None):
    child_has_restaurant = False
    for child in graph[node]:
        if child != parent:
            if dfs(child, node):
                child_has_restaurant = True
            else:
                graph[node].remove(child)
    return node in restaurants or child_has_restaurant


def bfs(root):
    queue = [(root, None)]
    while len(queue) > 0:
        node = queue.pop()
        print(node)
        for child in graph[node[0]]:
            if child != node[1]:
                queue.insert(0, tuple([child, node[0]]))

    queue = [(node[0], None, 0)]
    while len(queue) > 0:
        node = queue.pop()
        for child in graph[node[0]]:
            if child != node[1]:
                queue.insert(0, tuple([child, node[0], node[2] + 1]))
    print(node)

input = sys.stdin.readline
n, m = [int(i) for i in input().split()]
restaurants = {int(i) for i in input().split()}
graph = [set() for i in range(n)]
for i in range(n - 1):
    a, b = [int(i) for i in input().split()]
    graph[a].add(b)
    graph[b].add(a)
dfs(0)
bfs(0)