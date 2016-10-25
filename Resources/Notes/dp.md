# Dynamic Programming #

## Knapsack ##

### 0/1 Knapsack ###

Define `dp[i, w]` to be the maximum value that can be attained with weight less than or equal to `w` using the first `i`
items. Definition of `dp[i, w]`: 
* `dp[0, w] = 0`
* `dp[i, w] = dp[i - 1, w]`, if <code>w<sub>i </sub> > w</code>, otherwise
* <code>dp[i, w] = max(dp[i - 1, w], dp[i - 1, w - w<sub>i</sub>] + v<sub>i</sub>)</code>