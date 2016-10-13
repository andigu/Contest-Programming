import sys

sys.setrecursionlimit(100000000)


def solve(index):
    if index == n:
        return 0
    if dp[index] == -1:
        if index < n - 2:
            a = solve(index + 3) + shoes[index] + shoes[index + 1] + shoes[index + 2] - min(shoes[index:index + 3])
        else:
            a = sum(shoes[index:])
        if index < n - 1:
            b = solve(index + 2) + min(shoes[index: index + 2]) / 2 + max(shoes[index: index + 2])
        else:
            b = sum(shoes[index:])
        c = solve(index + 1) + shoes[index]
        dp[index] = min(a, b, c)
    return dp[index]


input = sys.stdin.readline
n = int(input())
shoes = [int(i) for i in input().split()]
dp = [-1] * n
print(format(round(solve(0), 1), '.1f'))
