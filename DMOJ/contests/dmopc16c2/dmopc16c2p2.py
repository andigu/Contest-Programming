import sys

sys.setrecursionlimit(1000000)


def dfs(currentClass):
    global sol
    if not visited[currentClass]:
        visited[currentClass] = True
        for x in graph[currentClass]:
            sol.add(x)
            for c in classesIn[x]:
                dfs(c)

input = sys.stdin.readline
n, m = [int(i) for i in input().split()]
graph = [set() for i in range(m)]
classesIn = [set() for i in range(n)]
for i in range(m):
    a = {int(i) - 1 for i in input().split()[1:]}
    graph[i] = a
    for x in a:
        classesIn[x].add(i)
visited = [False] * m
sol = set()
for c in classesIn[0]:
    dfs(c)
len = len(sol)
if len == 0:
    print(1)
    print(1)
else:
    print(len)
    print(" ".join(str(i + 1) for i in sorted(sol)))
