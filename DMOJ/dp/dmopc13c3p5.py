m, u, r = [int(i) for i in input().split()]
dp = [[0] * (u + 1) for i in range(m + 1)]
for _ in range(r):
    v, t, f = [int(i) for i in input().split()]
    dp = [[max(dp[i][j], dp[i - t][j - f] + v if i >= t and j >= f else 0) for j in range(u + 1)] for i in range(m + 1)]
print(dp[-1][-1])