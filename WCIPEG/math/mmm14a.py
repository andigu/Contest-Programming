def sieve(n):
    factors = [0] * n
    for i in range(2, n):
        if factors[i] == 0:
            factors[i] += 1
            for j in range(i * 2, n, i):
                factors[j] += 1
    return factors


a, b = int(input()), int(input())
n = sieve(b + 1)
for i in range(a, b + 1):
    print(n[i])
