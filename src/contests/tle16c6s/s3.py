import sys

sys.setrecursionlimit(1000000)
input = sys.stdin.readline


def solve(x, energy):
    ans = 0
    entered = False
    for loc in houses:
        if loc != x and energy - abs(loc - x) >= 0 and houses[loc] != 0:
            entered = True
            temp = houses[loc]
            houses[loc] = 0
            ans = max(ans, solve(loc, energy + temp - abs(loc - x)) + abs(loc - x))
            houses[loc] = temp
    if not entered:
        ans = energy
    return ans


houses = {}
for i in range(int(input())):
    data = [int(i) for i in input().split()]
    houses[data[0]] = data[1]
temp = houses[0]
houses[0] = 0
print(solve(0, temp))