import java.util.*;

public class Algorithm {

  public static List<Integer> findSubstring(String s, String[] words) {
    List<Integer> matchingIndices = new ArrayList<>();

    if (s == null || s.length() == 0 || words == null || words.length == 0) {
      return matchingIndices;
    }

    int textLength = s.length();
    int totalWordCount = words.length;
    int wordLength = words[0].length();
    int totalConcatLength = wordLength * totalWordCount;

    if (textLength < totalConcatLength) {
      return matchingIndices;
    }

    // Build target frequency map for the words array
    Map<String, Integer> targetWordCounts = new HashMap<>();
    for (String word : words) {
      targetWordCounts.put(word, targetWordCounts.getOrDefault(word, 0) + 1);
    }

    // Check every valid starting index in s
    for (int i = 0; i <= textLength - totalConcatLength; i++) {
      if (
        hasValidWordConcatenation(
          i,
          s,
          targetWordCounts,
          wordLength,
          totalConcatLength,
          totalWordCount
        )
      ) {
        matchingIndices.add(i);
      }
    }

    return matchingIndices;
  }

  private static boolean hasValidWordConcatenation(
    int startIndex,
    String text,
    Map<String, Integer> targetWordCounts,
    int wordLength,
    int totalConcatLength,
    int totalWordCount
  ) {
    Map<String, Integer> remainingWordCounts = new HashMap<>(targetWordCounts);
    int matchedWordsCount = 0;
    int currentPointer = startIndex;
    boolean isValidSequence = true;

    // Process fixed word chunks sequentially while matches remain valid
    while (currentPointer < startIndex + totalConcatLength && isValidSequence) {
      String wordChunk = text.substring(
        currentPointer,
        currentPointer + wordLength
      );
      int availableCount = remainingWordCounts.getOrDefault(wordChunk, 0);

      if (availableCount > 0) {
        remainingWordCounts.put(wordChunk, availableCount - 1);
        matchedWordsCount++;
        currentPointer += wordLength;
      } else {
        isValidSequence = false;
      }
    }

    return matchedWordsCount == totalWordCount;
  }

  public static void main(String[] args) {
    String s = "barfoofoobarthefoobarman";
    String[] words = { "bar", "foo", "the" };

    List<Integer> result = findSubstring(s, words);

    System.out.println("Matching start indices: " + result);
  }
}
