import java.util.*;

public class Algorithm {

  public static List<Integer> findSubstring(String s, String[] words) {
    List<Integer> matchingIndices = new ArrayList<>();

    if (s == null || s.length() == 0 || words == null || words.length == 0) {
      return matchingIndices;
    }

    int textLength = s.length();
    int targetWordCount = words.length;
    int wordLength = words[0].length();
    int totalConcatLength = wordLength * targetWordCount;

    if (textLength < totalConcatLength) {
      return matchingIndices;
    }

    Map<String, Integer> targetWordCounts = new HashMap<>();
    for (String word : words) {
      targetWordCounts.put(word, targetWordCounts.getOrDefault(word, 0) + 1);
    }

    // Process sliding window for each offset from 0 to wordLength - 1
    for (int offset = 0; offset < wordLength; offset++) {
      processSlidingWindow(
        offset,
        s,
        textLength,
        wordLength,
        totalConcatLength,
        targetWordCount,
        targetWordCounts,
        matchingIndices
      );
    }

    return matchingIndices;
  }

  private static void processSlidingWindow(
    int windowOffset,
    String text,
    int textLength,
    int wordLength,
    int totalConcatLength,
    int targetWordCount,
    Map<String, Integer> targetWordCounts,
    List<Integer> matchingIndices
  ) {
    Map<String, Integer> currentWindowWordCounts = new HashMap<>();
    int matchedWordsCount = 0;
    boolean hasExcessWord = false;
    int leftPointer = windowOffset;

    for (
      int rightPointer = windowOffset;
      rightPointer <= textLength - wordLength;
      rightPointer += wordLength
    ) {
      String wordChunk = text.substring(
        rightPointer,
        rightPointer + wordLength
      );

      if (!targetWordCounts.containsKey(wordChunk)) {
        currentWindowWordCounts.clear();
        matchedWordsCount = 0;
        hasExcessWord = false;
        leftPointer = rightPointer + wordLength;
      } else {
        while (
          rightPointer - leftPointer == totalConcatLength || hasExcessWord
        ) {
          String leftmostWordChunk = text.substring(
            leftPointer,
            leftPointer + wordLength
          );
          leftPointer += wordLength;

          int updatedCount = currentWindowWordCounts.get(leftmostWordChunk) - 1;
          currentWindowWordCounts.put(leftmostWordChunk, updatedCount);

          if (updatedCount >= targetWordCounts.get(leftmostWordChunk)) {
            hasExcessWord = false;
          } else {
            matchedWordsCount--;
          }
        }

        int currentCount =
          currentWindowWordCounts.getOrDefault(wordChunk, 0) + 1;
        currentWindowWordCounts.put(wordChunk, currentCount);

        if (currentCount <= targetWordCounts.get(wordChunk)) {
          matchedWordsCount++;
        } else {
          hasExcessWord = true;
        }

        if (matchedWordsCount == targetWordCount && !hasExcessWord) {
          matchingIndices.add(leftPointer);
        }
      }
    }
  }

  public static void main(String[] args) {
    String s = "barfoofoobarthefoobarman";
    String[] words = { "bar", "foo", "the" };

    List<Integer> result = findSubstring(s, words);

    System.out.println("Matching start indices for Optimised2: " + result);
  }
}
