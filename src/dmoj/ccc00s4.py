def solve(distance):
    if distance < 0:
        return float("inf")
    if distance == 0:
        return 0
    else:
        if distance not in dp:
            dp[distance] = min(solve(distance - i) for i in clubs) + 1
        return dp[distance]

dist = int(input())
clubs = [int(input()) for i in range(int(input()))]
dp = {}
result = solve(dist)
print("Roberta wins in " + str(result) +  " strokes." if result != float("inf") else "Roberta acknowledges defeat.")
