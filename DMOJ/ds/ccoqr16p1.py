import sys

input = sys.stdin.readline
n = int(input())

xPoints = []
yPoints = []
xa = xPoints.append
ya = yPoints.append
for _ in range(n):
    temp = input().split()
    xa(int(temp[0]))
    ya(int(temp[1]))
y = {value: index for index, value in enumerate(sorted(set(yPoints)))}
if len(y) == n:  # if all points are unique, there are no bowties
    print(0)
else:
    x = {value: index for index, value in enumerate(sorted(set(xPoints)))}
    horizontal = [[] for i in range(len(x))]
    vertical = [[] for i in range(len(y))]
    for i in range(n):
        horizontal[x[xPoints[i]]].append(y[yPoints[i]])
        vertical[y[yPoints[i]]].append(x[xPoints[i]])

    del x
    del y
    del xPoints
    del yPoints

    for i in vertical:
        i.sort()
    for i in horizontal:
        i.sort()
    vertical = [{value: index for index, value in enumerate(i)} for i in vertical]

    result = 0
    row = 0
    for row, index in enumerate({value: index for index, value in enumerate(i)} for i in horizontal):
        length = len(index)
        for coordinate in index:
            if row in vertical[coordinate]:
                left = index[coordinate]
                right = length - left - 1
                up = vertical[coordinate][row]
                down = len(vertical[coordinate]) - up - 1
                result += left * up * right * down + right * up * left * down
    print(result)