import sys

input = sys.stdin.readline
n = int(input())
counts = [[0, i] for i in range(1001)]
for i in range(n):
    counts[int(input())][0] += 1
counts = sorted([i for i in counts if i[0] != 0], key=lambda x: (x[0], x[1]), reverse=True)

if counts[0][0] == counts[1][0]:
    a = [i[1] for i in counts if i[0] == counts[0][0]]
    print(max(a) - min(a))
else:
    a = [i[1] for i in counts if i[0] == counts[1][0]]
    print(max(abs(max(a) - counts[0][1]), abs(min(a) - counts[0][1])))
