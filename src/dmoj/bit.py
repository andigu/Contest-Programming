# ALL INDEXES ARE 1-BASED


def update(i, tree, value):
    while i < n + 1:
        tree[i] += value
        i += i & (-i)


# i is the i th element in the original input array (i=3 means the 3rd element)
def get_sum(i, tree):
    ans = 0
    while i > 0:
        ans += tree[i]
        i -= i & (-i)
    return ans


n = int(input())
inputArray = [int(x) for x in input().split(' ')]
inputArray.insert(0, 0)  # insert dummy node to have 1-based indexing

BIT = [0] * (n + 1)
for i in range(1, n + 1):
    update(i, BIT, inputArray[i])
print(BIT)
print(get_sum(3, BIT))
