import sys


input = sys.stdin.readline
n = int(input())
hist = set()
count = {}
ans = 0
for i in range(n):
    a, b = [int(i) for i in input().split()]
    if (a, b) in hist:
        print("Infinity")
        break
    else:
        cnt = count.get(a, 0)
        ans += (i - cnt)
        count[a] = cnt + 1
    hist.add((a, b))
else:
    print(ans)
