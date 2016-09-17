import sys


def next_locations(x, y):
    for a, b in {(1, 0), (-1, 0), (0, 1), (0, -1)}:
        m, n = x + a, y + b
        if 0 <= m < r and 0 <= n < c and matrix[m][n] > matrix[x][y]:
            yield m, n


def best(x, y):
    if dp[x][y] is None:
        dp[x][y] = matrix[x][y] + max([best(a, b) for a, b in next_locations(x, y)] or [0])
    return dp[x][y]

input = sys.stdin.readline
c, r, n = [int(i) for i in input().split()]
matrix = [[0] * c for i in range(r)]
dp = [[None] * c for i in range(r)]
for i in range(n):
    y, x, w, h, sets = [int(i) for i in input().split()]
    x -= 1
    y -= 1
    for row in range(h):
        value = (row + 1) * sets
        increment = value
        for column in range(w):
            matrix[x + row][y + column] += value
            value += increment
a, b = [int(i) - 1 for i in input().split()]
print(best(b, a) % 1000000007)
