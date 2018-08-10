for i in range(int(input())):
    d, data = input().split(' ')
    d = int(d)
    total = 0
    shot = 1
    n = len(data)
    instructions = []
    for j in range(n):
        if data[j] == 'C':
            shot *= 2
            instructions.append(-1)
        else:
            total += shot
            instructions.append(shot)

    swaps = 0

    for x in range(n):
        for j in range(n-1):
            if total <= d:
                break
            if instructions[j] == -1 and instructions[j + 1] > 0:
                total -= instructions[j + 1] / 2
                instructions[j] = instructions[j + 1] / 2
                instructions[j + 1] = -1
                swaps += 1

    if total > d:
        print('Case #' + str(i + 1) + ': IMPOSSIBLE')
    else:
        print('Case #' + str(i + 1) + ': ' + str(swaps))
