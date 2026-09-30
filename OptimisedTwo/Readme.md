# OptimisedTwo
This obervation is bette explained with an example: 
Take `words = [foo, bar, baz]` and `s = foobarbazfoo`. The string is four chunks, `foo, bar, baz, foo`, and two of them are answers: `foobarbaz and barbazfoo`. Those two share `bar` and `baz`. OptimisedOne.java, after a match, stated to compute the count again at the next start and reworks those middle chunks, this computation was done per helper function call, and got garbage collected once the function was removed from the stack, hence wasting our precious cpu cycles. This version keeps two pointers. The left one sits on the start of the `current window` being calculated, the right one on the latest chunk, and both move by one chunk (this is again made possible, thanks to the constraint that each word is of the same length). `The counts for bar and baz stay`. That window in the middle of `s` is the `sliding window`.

# Time complexity
Left and right move by `W`, one chunk at a time.
Each move drops the leftmost chunk and appends the new rightmost chunk.
The shared chunks stay in the `counts`, so a start no longer rereads all `n` words.
One pass runs for each offset from `0` to `W - 1`.
Each character is read `O(W)` times because of the iteration, so the scan is `O(N × W)`.
The `n` in `O(N × n × W)` is not present anymore.

# Space Complexity
The word-count frequency map is still present.
The window map is updated in place, so it is no longer copied at every start !
So the space still is  `O(n)`.

# How this improves on the previous version
foobarbaz matches at index 0. The next answer on the same cuts is barbazfoo, one word later.
OptimisedOne recounts bar and baz for that second window.
Here the left pointer stays on the start of the window and the right pointer takes the next chunk, foo.
bar leaves, the new foo enters, and bar and baz are not rebuilt.
That reuse is the sliding window.