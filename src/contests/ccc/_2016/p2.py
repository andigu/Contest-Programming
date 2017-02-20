q = int(input())
n = int(input())
a, b = sorted([int(i) for i in input().split()]), sorted([int(i) for i in input().split()])
if q == 1:
    print(sum(max(a[i], b[i]) for i in range(n)))
else:
    print(sum(max(a[i], b[n - i - 1]) for i in range(n)))
