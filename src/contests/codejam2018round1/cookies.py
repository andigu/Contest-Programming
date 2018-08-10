from math import sqrt

for test in range(int(input())):
    n, p = [int(i) for i in input().split()]
    cookies = sorted([sorted([int(i) for i in input().split()]) for _ in range(n)], key=lambda x: x[0], reverse=True)
    o_p = sum(map(lambda x: 2 * x[0] + 2 * x[1], cookies))

    maximum_delta = sum(map(lambda x: 2 * sqrt(x[0] ** 2 + x[1] ** 2), cookies))
    prefix = 'Case #' + str(test + 1) + ': '
    if p < maximum_delta + o_p:
        if p - o_p < 2*cookies[0][0]:
            print(prefix + "%.6f" % o_p)
        else:
            print(prefix + "%.6f" % p)
    else:
        print(prefix + "%.6f" % (maximum_delta + o_p))
