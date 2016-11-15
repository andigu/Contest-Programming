import sys

input = sys.stdin.readline
max_size = int(input())
queue_len = int(input())
name = []
time = []
a = name.append
b = time.append
for i in range(queue_len):
    a(input().strip())
    b(int(input()))
dp = [0]
size = []
a = size.append
b = dp.append
for i in range(queue_len):
    best = float("inf")
    group_size = -1
    for j in range(i - max_size + 1 if i >= max_size else 0, i + 1):
        slowest = dp[j] + max(time[j : i +1])
        if slowest < best:
            best = slowest
            group_size = i - j + 1
    a(group_size)
    b(best)
current = len(size) - 1
hist = []
a = hist.append
while current >= 0:
    a(name[current - size[current] + 1: current + 1])
    current -= size[current]
print("Total Time:", dp[-1])
for names in reversed(hist):
    print(" ".join(names))