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