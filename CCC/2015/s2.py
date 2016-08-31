import sys

input = sys.stdin.readline
matcher = {"S": 0, "M": 1, "L": 2}
j = int(input())
a = int(input())
jerseys = [matcher[input().strip()] for i in range(j)]
most = 0
for i in ((input().split()) for _ in range(a)):
    if jerseys[int(i[1]) - 1] >= matcher[i[0]]:
        most += 1
        jerseys[int(i[1]) - 1] = -1
print(most)
