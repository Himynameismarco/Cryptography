package main.java.org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CaesearBreaking {

  private final String ALPHABET_ORDERED_AFTER_FREQUENCY = "ETAINOSHRDLUCMFWYGPBVKQJXZ";

  // Eyeball-Method
  /**
   * Gives back all potential combinations (26) that the message might have been encrypted in.
   *
   * @param encryptedMessage - the message in encrypted format.
   * @return - a list of all possible combinations as StringBuilder;
   */
  public ArrayList<StringBuilder> giveAllPotentialCombinations(StringBuilder encryptedMessage) {
    CaesarImplementation caesarImplementation = new CaesarImplementation();
    StringBuilder currentVersion = new StringBuilder();
    ArrayList<StringBuilder> allCombinations = new ArrayList<>();
    for (int i = 1; i < 27; i++) {
      currentVersion.append("Key ").append(i).append(": ");
      currentVersion.append(caesarImplementation.makeCaesar(encryptedMessage.toString(), i, true));
      System.out.println(currentVersion);
      allCombinations.add(currentVersion);
      currentVersion = new StringBuilder();
    }
    return allCombinations;
  }

  //Counting letters that most occur - in English most used letter will be 'e'
  public int getKeyToSetToMostCommonLetter() {
    return 0;
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
    CaesarImplementation caesarImplementation = new CaesarImplementation();
    String alphabet = caesarImplementation.getALPHABET_LOWER();
    for (int i = 0; i < wordList.length; i++) {
      System.out.println(wordList[i]);
      currentWord = wordList[i];
      for (int j = 0; j < currentWord.length(); j++) {
        currentIndexOfLetter = alphabet.indexOf(currentWord.charAt(j));
        if (currentIndexOfLetter > 0) {
          frequencyList.set(currentIndexOfLetter, frequencyList.get(currentIndexOfLetter) + 1);
        }
      }
    }

    for (int i = 0; i < alphabet.length(); i++) {
        System.out.println(frequencyList.get(i) + " times letter " + alphabet.charAt(i) + ".");
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

    System.out.println("The index of the most used letter is " + maxIndex + ". It is "
        + alphabet.charAt(maxIndex) + ".");
    return maxIndex;

  }

  public String breakCaesarBasedOnFrequentLetters(String encryptedMessage, int letterRank) {
    int mostCommonIndex = getIndexOfMostFrequentLetter(encryptedMessage);
    //index of the 'letterrank' most frequent letter, if letterrank is 0 it is 'e'
    CaesarImplementation caesarImplementation = new CaesarImplementation();
    Character characterToBaseEncryptionOn = ALPHABET_ORDERED_AFTER_FREQUENCY.charAt(letterRank);
    String alphabet = caesarImplementation.getALPHABET_UPPER();
    int indexOfCharInNormalAlphabet = alphabet.indexOf(characterToBaseEncryptionOn);
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
    String solution = caesarImplementation.makeCaesar(encryptedMessage, delta, moveLeft).toString();
    return solution;
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
        System.out.println(lengthList.get(i) + " words of length " + i + ".");
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

  public String readFileToString(String pathAsString) throws IOException {
    Path path = Path.of(pathAsString);
    String content = Files.readString(path, StandardCharsets.US_ASCII);
    StringBuilder stringBuilder = new StringBuilder(content);
    return content;
  }


}