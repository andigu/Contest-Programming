Primes
======
The number of primes under a number `n` can be approximated to be `n/ln(n)`.

Sieve of Eratosthenes
---------------------

The sieve is used to generate all primes up to `n`, and filter out composite numbers:
1. Create a list of consecutive integers from 2 through `n`: `2, 3, 4, ..., n`.
2. Initially, let `p` equal 2, the smallest prime number.
3. Enumerate the multiples of p by counting to `n` from `2p` in increments of `p`, and mark them in the list (these 
will be `2p, 3p, 4p, ... `; the `p` itself should not be marked).
4. Find the first number greater than `p` in the list that is not marked. If there was no such number, stop. Otherwise,
let `p` now equal this new number (which is the next prime), and repeat from step 3.

Matrices
========
Matrices are used in many contests, with two use cases:
* Matrix multiplication (or matrix powers)
* Gaussian elimination for systems of equations

Matrix Multiplication
---------------------
* Using plain multiplication, we get O(n<sup>3</sup>) time
* The lower bound, currently, is O(n<sup>2.36</sup>)
* It is possible to do fast matrix multiplication with DP (dynamic programming)
Use cases for matrix multiplication include:
* Adjacency matrices for graphs:
    * The adjacency matrix `A` of a graph `G` is a `V` by `V` matrix where `(R, C)` represents the number of ways to 
    get to node `C` from node `R` in **1** step.
    * The result of <code>A<sup>K</sup></code> is a `V` by `V` matrix where `(R, C)` represents the number of ways to 
    get to node `C` from node `R` in exactly **K** steps.
* Fibonacci numbers:
    * To find 

Gaussian Elimination
--------------------
It is possible to represent a system of equations with `n` equations with `n` variables as a `n` by `n` matrix:
```
x + y + z = 10
x + y - z = 4
x + 3y + z = 14
```
as
```
|1 1 1  10|
|1 1 -1  4|
|1 3 1  14|
```
Doing this series of steps:
* `R2 - R1`
* `R3 - R1`
* `R2/-2 - R3/2`
* Swap `R2` and `R3`
* `R1 - R2 - R3`

Implementing this:
* In <code>row<sub>i</sub></code>, only <code>column<sub>i</sub></code> should be non-zero.
