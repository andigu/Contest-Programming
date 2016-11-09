import sys

input = sys.stdin.readline
n, m, k = [int(i) for i in input().split()]
jugs = 0
hist = [0]
count = [0]
most = 0
source = 0
c_app = count.append
h_app = hist.append
mask = 1
for i in range(1, n):
    mask |= mask << 1

dp = [1 << i for i in range(n)]
cnt = 0
for i in range(1, m + 1):
    tokens = input().split()
    if tokens[0] == "X":
        jugs = ~jugs & mask
        cnt = n - cnt
    else:
        x = int(tokens[1]) - 1
        if tokens[0] == "F":
            new = jugs | dp[x]
            if new != jugs:
                cnt += 1
            jugs = new
        elif tokens[0] == "D":
            new = jugs & ~dp[x]
            if new != jugs:
                cnt -= 1
            jugs = new
        else:
            jugs = hist[x + 1]
            cnt = count[x + 1]
    c_app(cnt)
    h_app(jugs)
    print(cnt, max(count[max(i - k + 1, 0): i + 1]))
