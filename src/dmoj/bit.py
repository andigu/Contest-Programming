def update(index, a, tree, value):
    # index is index to be updated, a is input array / list, tree is BIT array, value is value to be added to original
    # number at index location
    add = value
    n = len(a)
    while index < n:
        tree[index] += add
        index = index + (index & (-index))


def getsum(index, a, tree):
    # index is location upto which you want the sum of elements from beginning
    # tree is BIT[], a is input array / list
    n = len(a)
    ans = 0
    while (index > 0):
        ans += tree[index]
        index = index - (index & (-index))
    return ans


n = int(input("Number of Elements in array: "))
inputArray = list(map(int, input("Elements in array: ").split()))
inputArray.insert(0, 0)  # insert dummy node to have 1-based indexing

BIT = []
for i in range(0, n):
    BIT.append(0)

for i in range(1, n):
    update(i, inputArray, BIT, inputArray[i])
