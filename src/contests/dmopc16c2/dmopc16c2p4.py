import sys


def trailing(x):
    count = 0
    i = 5
    while x / i >= 1:
        count += x // i
        i *= 5
    return count


def f(x):
    if x == 0:
        return 1
    else:
        lo = 1
        hi = sys.maxsize
        while lo < hi:
            middle = (lo + hi) // 2
            temp = trailing(middle)
            if temp < x:
                lo = middle + 1
            elif temp >= x:
                hi = middle
        return lo


a, b = [int(i) for i in input().split()]
print(f(b + 1) - f(a))
