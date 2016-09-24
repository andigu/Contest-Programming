import sys


def init(lst, n):
    array = [0] + lst
    for i in range(1, n):
        i2 = i + (i & -i)
        if i2 < n:
            array[i2] += array[i]
    return array


def range_query(tree, from_i, to_i):
    to_i += 1
    x = 0
    while to_i:
        x += tree[to_i]
        to_i -= to_i & -to_i
    while from_i:
        x -= tree[from_i]
        from_i -= from_i & -from_i
    return x

def update(tree, i, add, n):
    i += 1
    while i < n:
        tree[i] += add
        i += i & -i


input = sys.stdin.readline
n, m = [int(i) for i in input().split()]
n += 1
array = [int(i) for i in input().split()]
queries = [i.split() for i in sys.stdin.read().split("\n")][:-1]
largest = max(max((int(i[-1]) for i in queries)), max(array))
tree = init(array, n)
counts = [0] + [0] * largest
for i in array:
    counts[i] += 1
tree_count = init(counts, len(counts) + 1)

for query in queries:
    if query[0] == "C":
        index, value = [int(j) for j in query[1:]]
        index -= 1
        update(tree, index, value - array[index], n)
        update(tree_count, array[index], -1, len(counts) + 1)
        counts[array[index]] -= 1
        array[index] = value

        update(tree_count, value, 1, len(counts) + 1)
        counts[value] += 1
    elif query[0] == "S":
        a, b = [int(j) - 1 for j in query[1:]]
        print(range_query(tree, a, b))
    else:
        a = int(query[1])
        print(range_query(tree_count,0,a))
