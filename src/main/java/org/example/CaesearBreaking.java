package main.java.org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CaesearBreaking {

  private final String ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private final String ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
  private final String ALPHABET_ORDERED_AFTER_FREQUENCY = "ETAINOSHRDLUCMFWYGPBVKQJXZ";

  public CaesearBreaking() {
  }

  // Eyeball-Method
  /**
   * Gives back all potential combinations (26) that the message might have been encrypted in.
   *
   * @param encryptedMessage - the message in encrypted format.
   * @return - a list of all possible combinations as StringBuilder;
   */
  public ArrayList<StringBuilder> giveAllPotentialCombinations(StringBuilder encryptedMessage) {
    StringBuilder currentVersion = new StringBuilder();
    ArrayList<StringBuilder> allCombinations = new ArrayList<>();
    for (int i = 1; i < 27; i++) {
      CaesarImplementationOneKey caesarImplementationOneKey = new CaesarImplementationOneKey(i, true);
      currentVersion.append("Key ").append(i).append(": ");
      currentVersion.append(
          caesarImplementationOneKey.makeCaesar(encryptedMessage.toString()));
      //System.out.println(currentVersion);
      allCombinations.add(currentVersion);
      currentVersion = new StringBuilder();
    }
    return allCombinations;
  }

  /**
   * get the index of the most frequent letter in the encrypted message.
   *
   * @param encryptedMessage the message.
   * @return the index.
   */
  public int getIndexOfMostFrequentLetter(String encryptedMessage) {
    ArrayList<Integer> frequencyList = new ArrayList<>();
    //making a List with 30 0s
    for (int i = 0; i < 27; i++) {
      frequencyList.add(0);
    }
    String[] wordList = encryptedMessage.split("\\W+");
    String currentWord = "";
    int currentIndexOfLetter;
    for (int i = 0; i < wordList.length; i++) {
      //System.out.println(wordList[i]);
      currentWord = wordList[i];
      for (int j = 0; j < currentWord.length(); j++) {
        currentIndexOfLetter = ALPHABET_LOWER.indexOf(currentWord.charAt(j));
        if (currentIndexOfLetter >= 0) {
          frequencyList.set(currentIndexOfLetter, frequencyList.get(currentIndexOfLetter) + 1);
        }
      }
    }

    for (int i = 0; i < ALPHABET_LOWER.length(); i++) {
        //System.out.println(frequencyList.get(i) + " times letter " + ALPHABET_LOWER.charAt(i) + ".");
    }

    int findMax = 0;
    int counter = 0;
    int maxIndex = 0;
    for (int count : frequencyList) {
      if (count > findMax) {
        findMax = count;
        maxIndex = counter;
      }
      counter++;
    }

    //System.out.println("The index of the most used letter is " + maxIndex + ". It is "
    //    + ALPHABET_LOWER.charAt(maxIndex) + ".");
    return maxIndex;

  }


  /**
   * breaks the caesar cipher based on the most frequent letter.
   *
   * @param encryptedMessage the message to be decrypted.
   * @param letterRank the letter rank, use 0 as default, it is 'e', the most used letter in the
   *                   english language --> see ALPHABET_ORDERED_AFTER_FREQUENCY
   * @return the message decrypted.
   */
  public String breakCaesarBasedOnFrequentLetters(String encryptedMessage, int letterRank) {
    int mostCommonIndex = getIndexOfMostFrequentLetter(encryptedMessage);
    //index of the 'letterrank' most frequent letter, if letterrank is 0 it is 'e'
    Character characterToBaseEncryptionOn = ALPHABET_ORDERED_AFTER_FREQUENCY.charAt(letterRank);
    int indexOfCharInNormalAlphabet = ALPHABET_UPPER.indexOf(characterToBaseEncryptionOn);
    int delta = 0;
    boolean moveLeft;
    if (indexOfCharInNormalAlphabet > mostCommonIndex) {
      moveLeft = false;
      delta = indexOfCharInNormalAlphabet - mostCommonIndex;
    } else {
      moveLeft = true;
      delta = mostCommonIndex - indexOfCharInNormalAlphabet;
    }
    System.out.println("delta is: " + delta);
    CaesarImplementationOneKey caesarImplementationOneKey = new CaesarImplementationOneKey(delta, moveLeft);
    String solution = caesarImplementationOneKey.makeCaesar(encryptedMessage).toString();
    return solution;
  }

  public int returnKeyForSlice(String slice) {
    int mostCommonIndex = getIndexOfMostFrequentLetter(slice);
    Character characterToBaseEncryptionOn = ALPHABET_ORDERED_AFTER_FREQUENCY.charAt(0);
    int indexOfCharInNormalAlphabet = ALPHABET_UPPER.indexOf(characterToBaseEncryptionOn);
    int delta = 0;
    boolean moveLeft;
    if (indexOfCharInNormalAlphabet > mostCommonIndex) {
      moveLeft = false;
      delta = indexOfCharInNormalAlphabet - mostCommonIndex;
    } else {
      moveLeft = true;
      delta = mostCommonIndex - indexOfCharInNormalAlphabet;
    }
    //System.out.println("delta is: " + delta);
    return delta;

  }



  /**
   * prints out the number of times a word length was in a given String.
   *
   * @param message - the string.
   * @return - the most common word length (e.g. "Hi" --> 2)
   */
  public int findMostCommonWordLength(String message) {

    ArrayList<Integer> lengthList = new ArrayList<>();
    //making a List with 30 0s
    for (int i = 0; i < 31; i++) {
      lengthList.add(0);
    }
    String[] wordList = message.split("\\W+");
    int currentLength;
    int currentCount;
    for (String word : wordList) {
      currentLength = word.length();
      currentCount = lengthList.get(currentLength);
      currentCount++;
      lengthList.set(currentLength, currentCount);
    }
    for (int i = 1; i < lengthList.size(); i++) {
      if (lengthList.get(i) != 0) {
        //System.out.println(lengthList.get(i) + " words of length " + i + ".");
      }
    }
    int findMax = 0;
    int maxIndex = 0;
    int counter = 0;
    for (int count : lengthList) {
      if (count > findMax) {
        findMax = count;
        maxIndex = counter;
      }
      counter++;
    }
    System.out.println("The length of the word most commonly used was: " + maxIndex);
    return maxIndex;
  }

  /**
   * Splitting up a String in two halves.
   *
   * @param encryptedMessage the string to be split.
   * @param half if 0 creates first half, if 1 creates second half.
   * @return the respective half.
   */
  private String halfOfString(String encryptedMessage, int half) {
    String theHalf = "";
    for (int i= 0; i < encryptedMessage.length(); i++) {
      if (i % 2 == half) {
        theHalf += encryptedMessage.charAt(i);
      }
    }
    return theHalf;
  }


  /**
   * Method to break Caesar knowing the keys.
   *
   * @param encryptedMessage
   * @param key1
   * @param key2
   * @param leftShift
   * @return
   */
  public String breakCaesarKnowingKeys(String encryptedMessage, int key1, int key2, boolean leftShift) {
    String firstHalf = halfOfString(encryptedMessage, 0);
    String secondHalf = halfOfString(encryptedMessage, 1);
    CaesarImplementationOneKey caesarImplementationOneKey1 = new CaesarImplementationOneKey(key1, leftShift);
    CaesarImplementationOneKey caesarImplementationOneKey2 = new CaesarImplementationOneKey(key2, leftShift);
    String decryptedFirst = caesarImplementationOneKey1.makeCaesar(firstHalf).toString();
    String decryptedSecond = caesarImplementationOneKey2.makeCaesar(secondHalf).toString();
    return addToHalvesTogether(decryptedFirst, decryptedSecond);
  }

  /**
   * adding the halves together, with each even letter being taken from the first half and each
   * uneven from the second half.
   * @param decryptedFirst
   * @param decryptedSecond
   * @return the two Strings put together.
   */
  private String addToHalvesTogether(String decryptedFirst, String decryptedSecond) {
    String added = decryptedFirst + decryptedSecond;
    StringBuilder solution = new StringBuilder();
    int counterFirst = 0;
    int counterSecond = 0;
    for (int i = 0; i < added.length(); i++) {
      if (i % 2 == 0) {
        solution.append(decryptedFirst.charAt(counterFirst));
        counterFirst++;
      } else {
        solution.append(decryptedSecond.charAt(counterSecond));
        counterSecond++;
      }
    }
    String solutionAsString = solution.toString();
    return solutionAsString;
  }

  /**
   * breaks the caesar cipher based on the most frequent letter 'e'.
   * Check method breakCaesarBasedOnFrequentLetters for more information.
   *
   * @param encryptedMessage the message to be decrypted.
   * @return the message decrypted.
   */
  public String breakCaesarForTwoKeysUsingMostFrequentLetter(String encryptedMessage) {
    String firstHalf = halfOfString(encryptedMessage, 0);
    String secondHalf = halfOfString(encryptedMessage, 1);
    String decryptedFirst = breakCaesarBasedOnFrequentLetters(firstHalf, 0);
    //System.out.println("Decrypted first: " + decryptedFirst);
    String decryptedSecond = breakCaesarBasedOnFrequentLetters(secondHalf, 0);
    //System.out.println("Decrypted second: " + decryptedSecond);
    return addToHalvesTogether(decryptedFirst, decryptedSecond);
  }


}
