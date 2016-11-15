__status__ = "TLE"

import sys


def bisect_right(a, x):
    lo = 0
    hi = i
    while lo < hi:
        mid = (lo + hi) // 2
        if x < a[mid]:
            hi = mid
        else:
            lo = mid + 1
    return lo


input = sys.stdin.readline
n = int(input())
nodes = [int(input()) for _ in range(n)]
depth = [-1] * (n + 1)
total = 0
processed = []
ins = processed.insert
for i in range(n):
    node = nodes[i]
    lower = bisect_right(processed, node) - 1
    upper = lower + 1
    if lower == -1 and upper == i:
        depth[node] = 0
    elif upper == i:
        depth[node] = depth[processed[lower]] + 1
    elif lower == -1:
        depth[node] = depth[processed[upper]] + 1
    else:
        depth[node] = max(depth[processed[upper]], depth[processed[lower]]) + 1
    ins(upper, node)
    total += depth[node]
    print(total)
