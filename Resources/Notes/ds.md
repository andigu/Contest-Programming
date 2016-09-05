Difference Arrays
=================
Given an array of numbers, we can construct a new array by replacing each element by the difference between itself 
and the previous element, except for the first element, which we simply ignore. This is called the difference array,
because it contains the first differences of the original array. For example, the array `[9, 2, 6, 3, 1, 5, 0 7]` is 
converted to `[-7, 4, -3, -2, 4, -5, 7]`. Pseudocode to convert an array `A` to its difference array `D(A)`:
```
for (int i = 0; i < n-1; i++)
    D[i] = A[i+1] - A[i];
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