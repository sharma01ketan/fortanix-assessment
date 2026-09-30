# BruteForceOne

As for all algorithmic questions, we frist implement a Brute Force appraoch solution to it, just to test out and get a feel for, what are the basic data structures and algorithms which can be used to solved a problem, this helps us figure of the read and write `path` of the algorithm, I prefer using such approaches in the begining to understand what kind of computation is being repeated if any, and why kind of read and search operations are being performed in the algorithm, using that understanding, we can move from a brute force solution to say DP, if we have overalpping sub-problems, or binary search if we  have monotonic search space, or sliding window if we have repeated computation over the same set of data.

The algorithm implemented in the frist verion, is a plain simple Brute Force, The frist algorithm made for this is the Brute.java, in which we create all of the Permutations of the words[] array given to us as input.

We do this because, in this implementation in Algorithm.java, we create all of the permutations of the words array concatenated, and then check them in the string s given to us as input. 


# Time Complexity

Length of input string `s` = N
Number of `words` in `words[]` = n
Length of each string in words = `W`
Length of one concatenation = `L = n × W`
Generate every ordering (via permutation) is `n!` strings, each built by copying characters = `O(n! × L)`
The loop, on every index of input `s` is compared on every concat operation = `O(N × n! × L)`
Characters read per comparision = `O(L)`

Largest amongst the above = `O(N × n! × L)`
This is much larger than a normal factorial !

# Space Complexity

We store all of the permutations in memory `n!` of the strings of length `L` = `O(n! × L)`
And since we are using Recursion, which creates new stack entry on each `call` = `O(n)`

This is the worst case time complexity we will encounter in this project!