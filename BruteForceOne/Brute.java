import java.util.*;

public class Brute {

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
      result.add(current);
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
    String[] words = { "foo", "bar", "baz" };

    ArrayList<String> permutations = permute(words);

    for (String permutation : permutations) {
      System.out.println(permutation);
    }
  }
}
