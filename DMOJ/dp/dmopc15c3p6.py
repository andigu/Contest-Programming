import itertools

# Number of ways to draw semicircles with consecutive n dots
def ways(n):
    if n % 2 == 1:
        return 0
    elif n <= 2:
        return 1
    else:
        ans = 1
        for i in range(1, n - 1):
            ans += ways(i) * ways(n - i)
        return ans



n = int(input())
k = int(input())
pairs = set()
for i in range(k):
    a, b = [int(i) for i in input().split()]
    if (a - b) % 2 == 1:
        pairs.add((a, b))

result = 0
for i in range(len(pairs) + 1):
    for combination in itertools.combinations(pairs, i):
        flag = [False] * n
        for pair in combination:
            a, b = pair[0] - 1, pair[1] - 1
            flag[a] = True
            flag[b] = True
        count = 0
        ans = 1
        print(flag)
        for i in range(n):
            if flag[i]:
                ans *= ways(count)
                count = 0
            else:
                count += 1
        ans *= ways(count)
        result += ans
        print(list(combination), count, ways(count), result)


print(result)