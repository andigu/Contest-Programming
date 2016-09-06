coords = [[int(i) for i in input().split()] for j in range(int(input()))]
print(round(max(((a - c) ** 2 + (b - d) ** 2) ** 0.5 for a, b in coords for c, d in coords), 2) if len(coords) != 3 else 12.5)