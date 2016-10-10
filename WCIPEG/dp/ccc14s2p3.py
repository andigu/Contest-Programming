# dp[x][y] represents the number of ways werewolfs can be assigned under a subtree rooted at x,
# if it has y werewolfs in it, keeping two of these arrays for two cases: if the node is a
# werewolf or not


def dfs(current, w, werewolf=True):
    for child in children[current]:
        for k in range(1, w):
            if child < 0:
                temp = dfs(-child, k, True)
            else:
                temp = dfs(child, k, False)
            if werewolf:
                isnt_werewolf[current][k] += dfs(current, w - k - 1, True) * temp
            else:
                is_werewolf[current][k] += dfs(current, w - k, False) * \
                                           (dfs(child, k, False) + dfs(current, k, False))

    if werewolf:
        return is_werewolf[current][w]
    else:
        return isnt_werewolf[current][w]


n, w, m = [int(i) for i in input().split()]
children = [set() for i in range(n + 1)]
parent = [-1] * (n + 1)
roots = {i for i in range(1, n + 1)}
for i in range(m):
    x, y, z = input().split()
    y, z = int(y), int(z)
    roots.remove(z)
    if x == "D":
        children[y].add(-z)
        parent[z] = -y
    else:
        children[y].add(z)
        parent[z] = y

for node in roots:
    parent[node] = 0
    children[0].add(node)

is_werewolf = [[1] * (w + 1) for i in range(n + 1)]
isnt_werewolf = [[1] * (w + 1) for i in range(n + 1)]
print(dfs(0, w, False))
