def trailing(x):
    count = 0
    i = 5
    while x / i >= 1:
        count += x // i
        i *= 5
    return count
a, b = [int(i) for i in input().split()]
n = 1
ans = 0
x = trailing(n)
while x <= b:
    if x >= a:
        ans += 1
    n += 1
    x = trailing(n)
print(ans)
