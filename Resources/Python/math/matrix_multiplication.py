def matrix_chain_order(dimensions):
    n = len(dimensions) - 1
    m = [[0] * n for i in range(n)]
    s = [[0] * n for i in range(n)]
    for length in range(1, n):
        for i in range(n - length):
            j = i + length
            m[i][j] = float("inf")
            for k in range(i, j):
                cost = m[i][k] + m[k + 1][j] + dimensions[i] * dimensions[k + 1] * dimensions[j + 1]
                if cost < m[i][j]:
                    m[i][j] = cost
                    s[i][j] = k
    print(s)

matrix_chain_order([10, 30, 30, 5, 5, 60])