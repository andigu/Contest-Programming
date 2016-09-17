a, b = None, None
for i in range(int(input())):
    x = input().split()
    if len(x) > 1:
        x, y = x
        if x == "1":
            if a != y and b != y:
                if a is None:
                    a = y
                elif b is None:
                    b = y
                print("true")
            else:
                print("false")
        elif x == "2":
            if a == y:
                a = b
                b = None
                print("true")
            elif b == y:
                b = None
                print("true")
            else:
                print("false")
        elif x == "3":
            if a == y:
                print("0")
            elif b == y:
                print("1")
            else:
                print("-1")
    else:
        if a == "false" or b == "false":
            print("false", end=(" " if b is not None else "\n"))
        if a == "true" or b == "true":
            print("true")