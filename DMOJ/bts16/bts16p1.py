essay = input()
a, b = 0, 0
for i in essay:
    if "a" <= i <= "z":
        a += 1
    elif "A" <= i <= "Z":
        b += 1
if a > b:
    print(essay.lower())
elif b > a:
    print(essay.upper())
else:
    print(essay)