import sys
from bisect import insort


def update(x):
    if x != 0:
        global size, arr, left
        for i in range(size - 1, -1, -1):
            arr[i] += x
            if arr[i] < min:
                arr = arr[i + 1:]
                size = size - i - 1
                left += i + 1
                return


data = (i.split() for i in sys.stdin.read().strip().split("\n"))
n, min = [int(i) for i in data.__next__()]
arr = []
size = 0
left = 0
to_add = 0
for letter, number in data:
    number = int(number)
    if letter == "I" and number >= min:
        update(to_add)
        to_add = 0
        insort(arr, number, 0, size)
        size += 1
    elif letter == "A":
        if to_add < 0:
            update(to_add)
            to_add = 0
        to_add += number
    elif letter == "S":
        to_add -= number
    elif letter == "F":
        if to_add < 0:
            update(to_add)
            to_add = 0
            if number <= size:
                print(arr[-number])
            else:
                print(-1)
        else:
            if number <= size:
                print(arr[-number] + to_add)
            else:
                print(-1)
print(left)