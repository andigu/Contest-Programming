import itertools

n = int(input())
k = int(input())
pairs = set()
for i in range(k):
    a, b = [int(i) for i in input().split()]
    if a - b % 2 == 1:
        pairs.add((a, b))

for i in range(len(pairs)):
    a = itertools.combinations(pairs, i)