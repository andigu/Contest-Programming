from math import *

for i in range(int(input())):
    size = float(input())
    if size <= 1.414213:
        a = (size + sqrt(2 - size * size)) / 2

        x = 0.5 * a
        y = 0.5 * (size - a)
        print('Case #' + str(i + 1) + ':')
        print(x if x != 0 else 0, -y if y != 0 else 0, 0)
        print(-y if y != 0 else 0, -x if x != 0 else 0, 0)
        print(0, 0, 0.5)
