time_limit, food_limit, n = [int(i) for i in input().split()]
restaurants = [[int(i) for i in input().split()] for _ in range(n)]
dp = [[0] * (time_limit + 1) for i in range(n + 1)]
food = [[0] * (time_limit + 1) for i in range(n + 1)]
for i in range(1, n + 1):
    for t in range(time_limit + 1):
        if restaurants[i - 1][1] > t:
            dp[i][t] = dp[i - 1][t]
            food[i][t] = food[i - 1][t]
        else:
            if food[i - 1][t - restaurants[i - 1][1]] + restaurants[i - 1][2] > food_limit:
                dp[i][t] = dp[i - 1][t]
                food[i][t] = food[i - 1][t]
                print(i, t)
            else:
                if dp[i - 1][t - restaurants[i - 1][1]] + restaurants[i - 1][0] > dp[i - 1][t]:
                    dp[i][t] = dp[i - 1][t - restaurants[i - 1][1]] + restaurants[i - 1][0]
                    food[i][t] = food[i - 1][t - restaurants[i - 1][1]] + restaurants[i - 1][2]
                else:
                    dp[i][t] = dp[i - 1][t]
                    food[i][t] = food[i - 1][t]
for i in dp:
    print(i)
print()
for i in food:
    print(i)