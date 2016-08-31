import sys

input = sys.stdin.readline
a = []
append = a.append
pop = a.pop
for i in range(int(input())):
    b = int(input())
    if b > 0:
        append(b)
    elif len(a) > 0:
        pop()
print(sum(a))