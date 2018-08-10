import math
import sys

input = sys.stdin.readline

directions = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 0), (0, 1), (1, -1), (1, 0), (1, 1)]

for i in range(int(input())):
    a = int(input())
    n = math.ceil(math.sqrt(a))

    minimum = 500 - int((n - 1) / 2)
    maximum = int(500 + math.ceil((n - 1) / 2))

    matr = [[0] * 1000 for _ in range(1000)]
    c = [minimum + 1, minimum + 1]
    print(str(c[0]) + ' ' + str(c[1]))
    sys.stdout.flush()
    r = [int(x) for x in input().split()]
    while r != [0, 0]:
        matr[r[0] - 1][r[1] - 1] = 1
        should_revisit = False
        for d in directions:
            if matr[c[0] + d[0] - 1][c[1] + d[1] - 1] == 0:
                should_revisit = True
        if not should_revisit:
            if c[0] + 4 <= maximum:
                c = [c[0] + 3, c[1]]
            elif c[0] + 1 < maximum:
                c = [maximum - 1, c[1]]
            elif c[0] + 1 == maximum:
                c = [minimum + 1, min(c[1] + 3, maximum - 1)]

        print(str(c[0]) + ' ' + str(c[1]))
        sys.stdout.flush()
        r = [int(x) for x in input().split()]
