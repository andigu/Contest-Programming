import sys


def prefix_query(array, i):
    i += 1
    result = 0
    while i:
        result += array[i]
        i -= i & -i
    return result


def range_query(array, from_i, to_i):
    return prefix_query(array, to_i) - prefix_query(array, from_i - 1)


def update(array, i, add):
    i += 1
    while i < MAX + 1:
        array[i] += add
        i += i & -i


input = sys.stdin.readline
n, min = [int(i) for i in input().split()]
MAX = 100000
BIT = [0] * (MAX + 2)
delta = 0
lowest = 0
for _ in range(n):
    letter, number = input().split()
    number = int(number)
    if letter == "I" and number >= min:
        update(BIT, MAX - number + delta, 1)
    elif letter == "A":
        delta += number
    elif letter == "S":
        delta -= number
        if delta < lowest:
            lowest = delta
    elif letter == "F":
        lo, hi, mid = 1, MAX, -1
        while lo < hi:
            mid = (lo + hi) // 2
            total = prefix_query(BIT, mid)
            if total < number:
                lo = mid + 1
            else:
                hi = mid
        #print("", MAX - lo + delta, min - lowest, MAX - lo)
        print(MAX - lo + delta if min - lowest <= MAX - lo else -1)
print(range_query(BIT, MAX - (min - lowest), MAX))
