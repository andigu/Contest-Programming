def valid(i, j):
    if matrix[i][j] is not None:
        return matrix[i][j]
    else:
        if i >= j:
            return True
        else:
            a = i
            b = j
            while a <= b:
                z = totals[a + 1] - totals[i] - totals[j + 1] + totals[b]
                if z < 0:
                    a += 1
                elif z > 0:
                    b -= 1
                else:
                    if valid(i, a) and valid(a + 1, b - 1) and valid(b, j):
                        matrix[i][j] = True
                        return True
                    else:
                        a += 1
            matrix[i][j] = False
            return False



n = int(input())
riceballs = [int(i) for i in input().split()]
totals = [0] + [sum(riceballs[:i + 1]) for i in range(n)]

matrix = [[None] * n for i in range(n)]
print(max(totals[j + 1] - totals[i] for i in range(n) for j in range(i, n) if valid(i, j)))
