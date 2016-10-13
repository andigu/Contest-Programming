n = int(input())
arr = [int(i) for i in input().split()]
ans = 0
even = True
for i in arr:
    ans += ((i % 2 == 0) == even)
    even = not even
print(ans)
