for test in range(int(input())):
    r, c, h, v = [int(x) for x in input().split()]
    data = []
    for _ in range(r):
        data.append(input())
    success = False
    for i in range(1, r):
        for j in range(1, c):
            cakes = [0, 0, 0, 0]
            for m in range(r):
                for n in range(c):
                    if data[m][n] == '@':
                        if m < i and n < j:
                            cakes[0] += 1
                        elif m < i and n >= j:
                            cakes[1] += 1
                        elif m >= i and n < j:
                            cakes[2] += 1
                        else:
                            cakes[3] += 1
            success = success or (cakes[0] == cakes[1] == cakes[2] == cakes[3])
    print('Case #' + str(test + 1) + ": " + ("POSSIBLE" if success else "IMPOSSIBLE"))
