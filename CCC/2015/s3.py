import sys


def solve():
    data = [int(i) for i in sys.stdin.read().split("\n")[:-1]]
    requested = [0 for _ in range(data[0])]
    res = 0
    for i in range(data[1]):
        a = data[i + 2] - 1
        while a >= 0:
            if requested[a] == 0:
                requested[a] += 1
                res += 1
                break
            else:
                next_a = a - requested[a]
                requested[a] += 1
                a = next_a
        else:
            return res
    return res


print(solve())
