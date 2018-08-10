import sys

inp = sys.stdin.readline

for i in range(int(inp())):
    n = int(inp())
    L = [int(x) for x in inp().split()]
    done = False
    while not done:
        done = True
        for j in range(n - 2):
            if L[j] > L[j + 2]:
                done = False
                temp = L[j]
                L[j] = L[j + 2]
                L[j + 2] = temp
    sted = sorted(L)
    err = -1
    for j in range(n):
        if sted[j] != L[j]:
            err = j
            break
    print('Case #' + str(i + 1) + ': ' + ('OK' if err == -1 else str(err)))
