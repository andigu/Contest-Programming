import sys

input = sys.stdin.readline
for i in range(int(input())):
    n = int(input())
    trains = [int(input()) for _ in range(n)]
    needed = 1
    branch = []
    toContinue = True
    while toContinue:
        if needed == n:
            toContinue = True
            break
        t, b = len(trains), len(branch)
        a = trains[-1] if t > 0 else -1
        b = branch[-1] if b > 0 else -1
        if a == needed:
            trains.pop()
            needed += 1
        elif b == needed:
            branch.pop()
            needed += 1
        else:
            if t > 0:
                branch.append(trains.pop())
            else:
                toContinue = False
    print("Y" if toContinue else "N")

