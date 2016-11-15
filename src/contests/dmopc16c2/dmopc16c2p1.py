n = int(input())
k = int(input())
power = {"1": 0.5, "2": 1, "3": 5}
print("Continue" if sum(power[input()] for _ in range(k)) <= n else "Return")