n, m, tick_limit = [int(i) for i in input().split()]
room = []
current = [-1, -1]
for i in range(m):
    room.append([])
    for index, value in enumerate(input()):
        room[-1].append(value)
        if value == "O":
            current = [i, index]
ticks = 1
direction = [0, 1]
current[0] += direction[0]
current[1] += direction[1]
while 0 <= current[0] < m and 0 <= current[1] < n:
    if ticks > tick_limit:
        print("The observer stays within the grid.")
        break
    else:
        ticks += 1
        if room[current[0]][current[1]] == "\\":
            if direction[0] == 0:
                direction[0] = direction[1]
                direction[1] = 0
            else:
                direction[1] = direction[0]
                direction[0] = 0
            room[current[0]][current[1]] = "/"
        elif room[current[0]][current[1]] == "/":
            if direction[0] == 0:
                direction[0] = -direction[1]
                direction[1] = 0
            else:
                direction[1] = -direction[0]
                direction[0] = 0
            room[current[0]][current[1]] = "\\"
        elif room[current[0]][current[1]] == "-" and direction[1] == 0:
            direction[0] = -direction[0]
            room[current[0]][current[1]] = "|"
        elif room[current[0]][current[1]] == "|" and direction[0] == 0:
            direction[1] = -direction[1]
            room[current[0]][current[1]] = "-"
        current[0] += direction[0]
        current[1] += direction[1]
else:
    if ticks > tick_limit:
        print("The observer stays within the grid.")
    else:
        print("The observer leaves the grid after {:d} tick(s).".format(ticks))
