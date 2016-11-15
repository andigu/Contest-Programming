# Solves a system of equations where the array is in the form
# [coefficient 1, coefficient 2, coefficient 3, ... result]
# [coefficient 5, coefficient 6, coefficient 7  ... result]
# and so on
def gauss(array):
    n = len(array)
    for i in range(n):
        # Search for maximum in this column
        largest = abs(array[i][i])
        max_row = i
        for k in range(i + 1, n):
            if abs(array[k][i]) > largest:
                largest = abs(array[k][i])
                max_row = k
        # Swap maximum row with current row (column by column)
        for k in range(i, n + 1):
            temp = array[max_row][k]
            array[max_row][k] = array[i][k]
            array[i][k] = temp
        # Make all rows below this one 0 in current column
        for k in range(i + 1, n):
            c = -array[k][i] / array[i][i]
            for j in range(i, n + 1):
                if i == j:
                    array[k][j] = 0
                else:
                    array[k][j] += c * array[i][j]

    # Solve equation Ax=b for an upper triangular matrix A
    ans = [0 for _ in range(n)]
    for i in range(n - 1, -1, -1):
        ans[i] = array[i][n] / array[i][i]
        for k in range(i - 1, -1, -1):
            array[k][n] -= array[k][i] * ans[i]
    return ans


if __name__ == "__main__":
    from fractions import Fraction

    length = int(input())
    A = [[0 for j in range(length + 1)] for i in range(length)]
    for i in range(length):
        for j, a in enumerate(input().split(" ")):
            A[i][j] = Fraction(a)
    x = gauss(A)
    line = ""
    for i in range(length):
        line += str(x[i]) + "\t"
    print(line)
