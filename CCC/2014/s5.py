import sys

all_data = [[0, 0]] + [[int(i) for i in j.split()] for j in sys.stdin.read().split("\n")]
n = int(all_data[1][0]) + 1
pts = [[0, 0]] + [[int(i) for i in j.split()] for j in all_data[1:-1]]
pairs = sorted(
    [[(pts[a][0] - pts[b][0]) ** 2 + (pts[a][1] - pts[b][1]) ** 2, a, b] for a in range(n) for b in range(a + 1, n)])
best = [0] * n
pbest = [0] * n
pdist = [0] * n

for pair in pairs:
    d = pair[0]
    a = pair[1]
    b = pair[2]

    if d != pdist[a]:
        pdist[a] = d
        pbest[a] = best[a]
    if d != pdist[b]:
        pdist[b] = d
        pbest[b] = best[b]

    if a == 0:  # the origin is a special case because we cannot revisit it
        best[a] = max(best[a], pbest[b])
    else:
        best[a] = max(best[a], pbest[b] + 1)
        best[b] = max(best[b], pbest[a] + 1)

print(best[0] + 1)
