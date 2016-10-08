import sys

input = sys.stdin.readline


def down_solve(node):
    if down[node] == -1:
        down[node] = max([down_solve(x) for x in child[node]] + [0]) + 1
    return down[node]


def up_solve(node):
    if node == 0:
        return 1
    if up[node] == -1:
        up[node] = max(up_solve(parent[node]) + 1,
                       2 + max([down_solve(x) for x in child[parent[node]] if x != node] or [-2]))
    return up[node]

n = int(input())
parent = [-1] * n
child = [set() for i in range(n)]
for i in range(n - 1):
    a, b = sorted([int(i) - 1 for i in input().split()])
    parent[b] = a
    child[a].add(b)
down = [-1] * n
up = [-1] * n
for i in range(n):
    print(max(down_solve(i), up_solve(i)))
