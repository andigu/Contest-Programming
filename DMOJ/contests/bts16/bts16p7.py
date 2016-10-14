import sys

input = sys.stdin.readline
p, q, n = [int(i) for i in input().split()]
scores = []
append = scores.append
for i in range(n):
    append([int(i) for i in input().split()])
dp = {}
for i in range(int(input())):
    score = tuple(int(i) for i in input().split())
    if score not in dp:
        dp[score] = sum(all(distribution[k] >= score[k] for k in range(p)) for distribution in scores)
    print(dp[score])
