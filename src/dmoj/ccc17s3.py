n = int(input())
data = [int(x) for x in input().split(' ')]
l = [0] * 2001
for x in data:
    l[x] += 1
max_len, ways = 0, 0
for height in range(2, max(data) * 2 + 1):
    length = sum((min(l[j], l[height - j]) if j != height - j else int(l[j] / 2)) if height - j <= 2000 else 0 for j in
                 range(max(1, height - 2000), int(height / 2) + 1))
    ways += 1 if max_len == length else 0
    if length > max_len:
        ways = 1
        max_len = length
print(max_len, ways)
