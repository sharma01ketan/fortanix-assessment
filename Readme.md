# Finding every concatenation of the word list

The problem is to find every index in a string `s` where the dictionary words appear back to back, each word used once, in any order. Every word has the same length. All four classes run this sample, `s = "barfoofoobarthefoobarman"` with `words = ["bar", "foo", "the"]`. Each folder is one implementation, in the order the search was tightened. `BruteForceOne` keeps `Brute.java`, the generator the first search is built from.

**BruteForceOne.** `Brute` permutes the words by backtracking. A `used` flag marks a chosen word, and the current concatenation grows by appending that word. Every finished ordering is stored. `Algorithm` runs the same procedure and then walks `s`. At each index it cuts a slice of the concatenation length, shorter at the tail, and compares that slice with every stored string.

Let `N` be the length of `s`, `n` the number of words, `W` the word length, and `L = n × W`. Generation is `O(n! × L)`. The scan is `O(N × n! × L)`: every index is compared with every concatenation, and each comparison reads `O(L)` characters. The stored strings dominate memory at `O(n! × L)`. `Brute` never reads `s`.

**BruteForceTwo.** The loop stops at `s.length() - totalLength`, so a start is tried only when a full window still fits. The previous scan used its last `L - 1` starts on a tail shorter than every concatenation, and those comparisons could never succeed. A finished string is stored only when `result.contains` says it is new, so a repeated word no longer keeps one copy per ordering of the duplicates. The scan then walks a shorter list. Space is `O(P × L)`, where `P` is the number of distinct concatenations. With all distinct words, `contains` adds generation cost and `P` stays `n!`.

**OptimisedOne.** A window is valid when its chunks of length `W` are exactly the multiset of dictionary words. This version builds a frequency map and, at each legal start, copies the map and consumes chunks until a chunk is missing, a count runs out, or every word is matched. Worst-case time is `O((N - L + 1) × L)`, that is `O(N × n × W)`. Space is `O(n)` for the two maps. Counting replaces equality against every ordering, and `n!` leaves the bound.

**OptimisedTwo.** OptimisedOne still restarts at every index. A start and the start `W` characters later share `n - 1` aligned words, but the map is discarded and those words are hashed again. This version runs one sliding window for each offset from `0` to `W - 1`. The window advances by one word: drop the leftmost chunk, append the new rightmost chunk. Each character is read `O(W)` times, which is `O(N × W)`, and the factor of `n` from rereading a full concatenation at every index is gone. Space stays `O(n)`.

The path is four removals: the missing search, the short tail and the duplicate strings, the factorial list, then the repeated scan of overlapping windows.
