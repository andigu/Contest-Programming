import sys

input = sys.stdin.readline
n = int(input())
partners = {}
a, b = input().split(), input().split()
c, d = {value: key for key, value in enumerate(a)}, {value: key for key, value in enumerate(b)}
for name in a:
    if b[c[b[c[name]]]] != name or b[c[name]] == name:
        print("bad")
        break
else:
    print("good")
