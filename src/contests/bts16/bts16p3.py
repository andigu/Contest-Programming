n = int(input())
names = input().split()
count = 0
for i in range(n):
    count += 1
    for j in range(i + 1, n):
        if names[j][0] == names[j - 1][0]:
            count += 1
        else:
            break
print(count)
