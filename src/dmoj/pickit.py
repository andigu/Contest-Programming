import sys


def solve(numbers, length):
    if length == 2:
        return 0
    else:
        if numbers not in memo:
            memo[numbers] = max(
                solve(numbers[:i] + numbers[i + 1:], length - 1) + numbers[i - 1] + numbers[i] + numbers[i + 1]
                for i in range(1, length - 1))
        return memo[numbers]


queries = [tuple(int(j) for j in i.split()) for i in sys.stdin.read().split("\n")]
for i in queries[:-2]:
    memo = {}
    print(solve(i[1:], i[0]))
