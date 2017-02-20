def inside(x_int, y_int, x, y):
    return y <= -(y_int / x_int) * x + y_int


x1, y1 = [int(i) for i in input().split()]
x2, y2 = [int(i) for i in input().split()]
a1 = 2 * x1
b1 = 2 * y1

a2 = 2 * x2
b2 = 2 * y2

ans = 0

if inside(a1, b1, x2, y2):
    ans = max(a1 * b1 / 2, ans)
if inside(a2, b2, x1, y1):
    ans = max(a2 * b2 / 2, ans)
if ans == 0:
    if y1 == y2 or x1 == x2:
        print(0)
    else:
        m = (y1 - y2) / (x1 - x2)
        intercept = y1 - m * x1
        print(intercept * (-intercept / m) / 2)
else:
    print(ans)
