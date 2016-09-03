# Too slow
import sys

input = sys.stdin.readline

n = int(input())
t = int(input())
rects = [[int(i) for i in input().split()] for j in range(n)]

itox = sorted({i[0] for i in rects}.union({i[2] for i in rects}))
itoy = sorted({i[1] for i in rects}.union({i[3] for i in rects}))

xLen = len(itox)
yLen = len(itoy)

da = [[0] * len(itox) for i in range(yLen)]

for i in range(n): # process by rows
    for j in range(itoy.index(rects[i][1]), itoy.index(rects[i][3])):
        da[j][itox.index(rects[i][0])] += rects[i][4]
        da[j][itox.index(rects[i][2])] -= rects[i][4]
print(da)

ans = 0
for i in range(yLen - 1):
    for j in range(xLen - 1):
        da[i][j + 1] += da[i][j]
        if da[i][j] >= t:
            ans += (itoy[i + 1] - itoy[i]) * (itox[j + 1] - itox[j])

print(ans)