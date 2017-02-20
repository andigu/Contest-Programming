a, b = list(input()), list(input())
ans = True
for x in b:
    if x != "*":
        if x in a:
            a.remove(x)
            b.remove(x)
        else:
            ans = False
if len(b) != len(a):
    ans = False
print("A" if ans else "N")

