# OptimisedOne

A window matches when its successive chunks are exactly the multiset of dictionary words. This version counts those chunks. It never builds a permutation list.

## Time complexity

Let `N` be the length of `s`, `n` the number of words, `W` the word length, and `L = n × W`.

Building the target frequency map hashes each word once, `O(n × W)`. There are `N − L + 1` legal starts. Each start copies the map and then reads up to `n` chunks of length `W`. A full pass over one window is `O(L)`. Worst-case time is `O((N − L + 1) × L)`, which is `O(N × n × W)`. A chunk that is missing, or whose count is already used up, stops that window early, so many starts cost less than a full pass.

## Space complexity

Two maps from word to remaining count: the target map and a copy for the current window. That is `O(n)` entries. The permutation list and the recursion stack from the brute-force versions are gone.

## How this improves on the previous version

BruteForceTwo still pays for order. It materialises every distinct concatenation and asks whether the window equals one of those strings. Any order is legal, and duplicate words are what made so many of those strings identical, so the work grows with `P`, up to `n!`. This version treats the window as a sequence of `n` tokens of length `W` and consumes them against a frequency map. A hash lookup replaces equality against the whole list, which is why `n!` leaves the bound. The same end condition is kept: a start is tried only when `L` characters remain. Empty input, and a string shorter than `L`, return before any scan.

# OptimisedOne

In this we move away from the brute force approach, and decide to use the approach that, if i itearte over `s`, then is the substring which i am currently checking on `s` a valid permutation of words or not, this approach is made possible by the constraint in the question that for all the words in `words[]` the length os the strings will be the same, if that wasn't the case, this approach would not be as optimised and substrings of all possible lengths would be needed to `"iterated" upon`. In this, instead of the permute function, we use a frequency map, implemented using a HashMap for `O(1)` lookup.

# Time complexity
One count of the words is built first, `O(n × W)`.
One the loop of `N − L + 1`, the count is copied and we read `n` chunks of length `W`.
A missing chunk, or a chunk whose mapping in the frequency map is down to 0, stops the window
A window which is able to consume all of the frequency map is a match
The full pass over the window becomes `O(L)`, so the comparision the permuted `P` is removed!
This in worst case will be `O((N − L + 1) × L)` which is basically `O(N × n × W)`

# Space Complexity
The stored concatenations `O(P × L)` are gone, removing the biggest space hogger
Two maps remain, the `word counts` and a `copy for the current window`, `O(n)` in the worst case.

# How this improves on the previous version
We removed the brute force permutation, that is now a frequency map lookup which is iterated upon
Since every `word` has the same length, hence the window is split into `n` chunks of length `W`.
The frequency map gives `O(1)` (we ignore the hashmap clases here) lookup in place of comparing the window with all `P` strings
