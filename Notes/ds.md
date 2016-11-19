# Difference Arrays #

Given an array of numbers, we can construct a new array by replacing each element by the difference between itself 
and the previous element, except for the first element, which we simply ignore. This is called the difference array,
because it contains the first differences of the original array. For example, the array `[9, 2, 6, 3, 1, 5, 0 7]` is 
converted to `[-7, 4, -3, -2, 4, -5, 7]`. To convert an array `A` of length `n` to its difference array `D(A)`:
```
for i in range(n):
    D[i] = A[i+1] - A[i]
```
[Read more on the WCIPEG wiki](http://wcipeg.com/wiki/Prefix_sum_array_and_difference_array) <br />

**The difference array is used to keep track of an array when ranges of said array can be updated all at once.** 
If we have array `A` and add an increment `k` to elements
<code>A<sub>i</sub>, A<sub>i+1</sub>, ..., A<sub>j-1</sub></code>, then notice: 
* <code>D<sub>0</sub>, D<sub>1</sub>, ..., D<sub>i-2</sub></code> are not affected
* <code>D<sub>i</sub>, D<sub>i+1</sub>, ..., D<sub>j-2</sub></code> are not affected
* <code>D<sub>j</sub>, D<sub>j+1</sub>, ...</code> are not affected
* <code>D<sub>i-1</sub> = A<sub>i</sub> - A<sub>i-1</sub></code> is increased by `k`
* <code>D<sub>j-1</sub> = A<sub>j</sub> - A<sub>j-1</sub></code> is decreased by `k`

Thus, if we are required to update many ranges of an array in this manner, we should keep track of `D` rather than `A` 
itself, and then integrate at the end to reconstruct `A`.

# Coordinate Compression #

Coordinate compression is a procedure that takes some points and reassigns their coordinates to remove "gaps". 
For example, if point `P1` is located at `x = 5`, point `P2` is located at `x = 27`, and point `P3` is located at 
`x = 65`, then, after coordinate compression, P1 may be located at `x = 0`, `P2` may be located at `x = 1`, and `P3` 
may be located at `x = 2`. The reason we compress coordinates is to get rid of all the "empty space" between points. 
This makes it easier to, say, use the coordinates as indices into an array. If we used the original numbers, 
we'd waste a lot of entries.

To perform coordinate compression, you make a list of all the coordinates and sort them in ascending order.
This gives you the rank of each coordinate. You then replace each coordinate by its rank. This takes `O(N log N)` time, 
so it's pretty efficient. To compress a list of `n` coordinates `C`:
```
x = [value: index for index, value in enumerate(sorted(set(i[0] for i in C)))}
y = [value: index for index, value in enumerate(sorted(set(i[1] for i in C)))}
for i in range(n):
    C[i][0] = x[i]
    C[i][1] = y[i]
```

# Disjoint Sets #

A disjoint-set data structure, also called a union–find data structure or merge–find set, is a data structure that 
keeps track of a set of elements partitioned into a number of disjoint (**non-overlapping**) subsets. It supports two 
operations:

* Find: Determine which subset a particular element is in. Find typically returns an item from this set that serves as
its "representative"; by comparing the result of two Find operations, one can determine whether two elements are in 
the same subset.
* Union: Join two subsets into a single subset.

One common approach is to select a fixed element of each set, called its representative, to represent the set as a
whole. Then, find(x) returns the representative of the set that x belongs to, and Union takes two set representatives
as its arguments.