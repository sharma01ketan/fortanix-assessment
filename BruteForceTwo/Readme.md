# BruteForceTwo

This workes roughly on the same algorithm, but with some small optimisations, which helps us in the further versions of this problem, hence it's getting some individual attention here

# Time complexity
`P` is the toatl number od distinct concats. `P` is `n!` when all words are unique, and smaller when some repeat
The optimisation is that, each string is checked before we store it
When all ordering is unique, the generation becomes `O((n!)^2 × L)`
The scan is `N − L + 1` and compares with all `P` strings which becomes `O((N − L + 1) × P × L)`

# Space Complexity
Just like the previous Brute Force algo `O(P × L)`

# How this improves on the previous version
This version stops at `s.length() - totalLength` where totalLength is the length of the acceptatble concatenation, so this reduces the `search space`
This version checks the `result` before entering a new one, so the scan walks each distinct concatenation once
This filter pays off because, with all distinct words, `contains` bounds `P` to `n!`