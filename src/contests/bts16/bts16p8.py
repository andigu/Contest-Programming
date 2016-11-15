n = int(input())

matrix = [[False] * 20 for i in range(20)]
for i in range(n):
    start, width, height = [int(i) for i in input().split()]
    start -= 1
    temp = start + width - len(matrix[0])
    if temp > 0:
        for j in range(len(matrix)):
            matrix[j] += [False] * temp
    temp = 0
    for j in range(len(matrix) - 1, -1, -1):
        if matrix[j][start]:
            temp = j + 1
            break
    delta = temp + height - len(matrix)
    for _ in range(delta):
        matrix.insert(0, [False] * len(matrix[0]))
    temp += delta
    for j in range(temp, temp + height):
        for k in range(start, start + width):
            matrix[j][k] = True
for i in matrix:
    print(["0" if a else "1" for a in i])



