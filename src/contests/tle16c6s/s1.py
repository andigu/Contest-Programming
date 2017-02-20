import sys

input = sys.stdin.readline
types = {input(): i for i in range(int(input()))}

problems = [(types[input()], i) for i in range(int(input()))]
problems = sorted(problems, key=lambda x: x[0])
for i in problems:
    print(i[1] + 1)