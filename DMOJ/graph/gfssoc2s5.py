import sys

sys.setrecursionlimit(1000000000)


def strongly_connected_component(graph):
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


def solve(current, take):
    if current == end:
        return val[current] * take, 1
    else:
        if dp[current][take] != -1 and cnt[current][take] != -1:
            return dp[current][take], cnt[current][take]
        else:
            a, b = -float("inf"), 0
            for v in province_graph[current]:
                if take:
                    m, n = solve(v, False)
                    m += val[current]
                    if m > a:
                        a = m
                        b = n
                    elif m == a:
                        b += n
                else:
                    m, n = solve(v, True)
                    o, p = solve(v, False)
                    if m > a:
                        a = m
                        b = n
                    elif m == a:
                        b += n
                    if o > a:
                        a = o
                        b = p
                    elif o == a:
                        b += p
            dp[current][take] = a
            cnt[current][take] = b % 1000000007
            return a, b


input = sys.stdin.readline
n, m = [int(i) for i in input().split()]
loot = [int(i) for i in input().split()]
graph = [set() for i in range(n)]
for i in range(m):
    a, b = [int(i) - 1 for i in input().split()]
    if a != b:
        graph[a].add(b)
component = strongly_connected_component(graph)
provinces = {}
val = {}
province_graph = {}
start, end = -1, -1
for node in component:
    if node == 0:
        start = component[node]
    elif node == n - 1:
        end = component[node]
    if component[node] not in provinces:
        provinces[component[node]] = set()
        val[component[node]] = loot[node]
        province_graph[component[node]] = {component[v] for v in graph[node] if component[v] != component[node]}
    else:
        provinces[component[node]].add(node)
        val[component[node]] += loot[node]
        province_graph[component[node]] = province_graph[component[node]].union(
            {component[v] for v in graph[node] if component[v] != component[node]})
n = len(provinces)
dp = {v: [-1, -1] for v in provinces.keys()}
cnt = {v: [-1, -1] for v in provinces.keys()}
x, y = solve(start, True)
a, b = solve(start, False)
if x == a:
    print(x, y + b)
elif x > a:
    print(x, y)
else:
    print(a, b)