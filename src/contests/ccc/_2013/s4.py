import sys

sys.setrecursionlimit(100000)


def recurse(config):
    print(config)
    temp = tuple(tuple(i) for i in config)
    if temp in dp:
        return dp[temp]
    if config == final:
        return 0
    ways = INF
    for i in range(n):
        if config[i][-1] != INF:
            if i < n - 1 and config[i][-1] < config[i + 1][-1]:
                config[i + 1].append(config[i].pop())
                copy = tuple(tuple(i) for i in config)
                if copy not in hist:
                    hist.append(copy)
                    res = recurse(config)
                    dp[copy] = res
                    ways = min(ways, res + 1)
                    hist.remove(copy)
                config[i].append(config[i + 1].pop())
            if i > 0 and config[i][-1] < config[i - 1][-1]:
                config[i - 1].append(config[i].pop())
                copy = tuple(tuple(i) for i in config)
                if copy not in hist:
                    hist.append(copy)
                    res = recurse(config)
                    dp[copy] = res
                    ways = min(ways, res + 1)
                    hist.remove(copy)
                config[i].append(config[i - 1].pop())
    return ways


n = int(input())
while n != 0:
    dp = {}
    INF = float("inf")
    final = sorted([INF, i + 1] for i in range(n))
    config = [[INF, int(i)] for i in input().split()]
    hist = []
    x = recurse(config)
    print(x if x != INF else "IMPOSSIBLE")
    n = int(input())
