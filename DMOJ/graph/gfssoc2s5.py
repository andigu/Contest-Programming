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

    def assign(u, r):
        if u not in component:
            component[u] = r
            for v in graph[u]:
                assign(v, r)

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
            max_loot, ways = -float("inf"), 0
            for v in province_graph[current]:
                if take:
                    m, n = solve(v, False)
                    m += val[current]
                    if m > max_loot:
                        max_loot = m
                        ways = n
                    elif m == max_loot:
                        ways += n
                else:
                    m, n = solve(v, True)
                    o, p = solve(v, False)
                    if m > max_loot:
                        max_loot = m
                        ways = n
                    elif m == max_loot:
                        ways += n
                    if o > max_loot:
                        max_loot = o
                        ways = p
                    elif o == max_loot:
                        ways += p
            dp[current][take] = max_loot
            cnt[current][take] = ways % 1000000007
            return max_loot, ways


def build_province_graph(node):
    if not visited[node]:
        visited[node] = True
        if component[node] not in province_graph:
            province_graph[component[node]] = set()
        for v in graph[node]:
            if component[v] != component[node]:
                province_graph[component[node]].add(component[v])
            build_province_graph(v)


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
    else:
        provinces[component[node]].add(node)
        val[component[node]] += loot[node]
visited = [False] * n
build_province_graph(0)
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
