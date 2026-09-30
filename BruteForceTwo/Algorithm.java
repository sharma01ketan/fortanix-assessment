import java.util.*;

public class Algorithm {

  public static List<Integer> findSubstring(String s, String[] words) {
    List<Integer> result = new ArrayList<>();

    // Generate all possible concatenated strings
    ArrayList<String> permutations = permute(words);
    System.out.println("permutations::: " + permutations);

    int wordLength = words[0].length();
    int totalLength = wordLength * words.length;

    // Optimization 1:
    // Only check starting positions where the full substring can fit
    for (int i = 0; i <= s.length() - totalLength; i++) {
      String current = s.substring(i, i + totalLength);

      // Compare against every permutation
      for (String permutation : permutations) {
        if (current.equals(permutation)) {
          result.add(i);
          break;
        }
      }
    }

    return result;
  }

  public static ArrayList<String> permute(String[] words) {
    ArrayList<String> result = new ArrayList<>();
    boolean[] used = new boolean[words.length];

    helper(words, 0, used, "", result);

    return result;
  }

  private static void helper(
    String[] words,
    int index,
    boolean[] used,
    String current,
    ArrayList<String> result
  ) {
    // We have selected every word
    if (index == words.length) {
      // Optimization 2:
      // Avoid duplicate concatenated strings
      if (!result.contains(current)) {
        result.add(current);
      }

      return;
    }

    for (int i = 0; i < words.length; i++) {
      if (used[i]) {
        continue;
      }

      // Choose
      used[i] = true;

      // Recurse
      helper(words, index + 1, used, current + words[i], result);

      // Undo choice
      used[i] = false;
    }
  }

  public static void main(String[] args) {
    // Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
    String s = "barfoofoobarthefoobarman";

    String[] words = { "bar", "foo", "the" };

    List<Integer> result = findSubstring(s, words);

    System.out.println(
      "Matching start indices for full product suite: " + result
    );
  }
}
