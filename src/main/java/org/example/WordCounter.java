package main.java.org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WordCounter {

  private HashMap<String, Integer> wordsFreqMap;
  private HashMap<String, Integer> charactersFreqMap;

  private Helper helper = new Helper();

  public WordCounter() {
    this.charactersFreqMap = new HashMap<>();
    this.wordsFreqMap = new HashMap<>();


  }

  public HashMap<String, Integer> createListOfDifferentWords(String string, boolean isPath) throws IOException {

    String toBeSplitted = "";
    if (isPath) {
      toBeSplitted = helper.readFileToString(string);
    } else {
      toBeSplitted = string;
    }
    String[] allWords = toBeSplitted.split("\\s+");
    for (String word : allWords) {
      word = word.toLowerCase();
      if (!wordsFreqMap.containsKey(word)) {
        wordsFreqMap.put(word, 1);
      } else {
        wordsFreqMap.put(word, wordsFreqMap.get(word) + 1);
      }
    }
    return wordsFreqMap;
  }

  public String getWordWithMostOccurrences() {
    int max = 0;
    String word = "";
    int wordLength = 0;
    int freq;
    for (String key : wordsFreqMap.keySet()) {
      freq = wordsFreqMap.get(key);
      if (freq > max) {
        word = key;
        max = freq;
        wordLength = key.length();
      }
    }
    System.out.println("How many words in file: " + wordsFreqMap.size());
    String solution = "Word: " + word + ", length: " + wordLength + ", frequency: " + max;
    return solution;
  }

  public String getCharacterWithMostOccurrences() {
    int max = 0;
    String word = "";
    String character = "";
    int freq;
    for (String key : wordsFreqMap.keySet()) {
      word = key;
      word = word.toUpperCase();
      if (charactersFreqMap.containsKey(word)) {
        freq = wordsFreqMap.get(key);
        if (freq > max) {
          max = freq;
          character = word;
        }
      }
    }
    String solution = "Character: " + character + ", frequency: " + max;
    return solution;
  }

  private void updateCharacters(String character) {
    if (!charactersFreqMap.containsKey(character)) {
      //System.out.println("Add " + character + " to characters.");
      charactersFreqMap.put(character, 1);
    } else {
      charactersFreqMap.put(character, charactersFreqMap.get(character) + 1);
    }
  }

  public void findAllCharacters(String filePath) throws IOException {
    File file = new File(filePath);
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        //System.out.println(line);
          String[] thisLinesWords = line.split("\\s+");
          if (thisLinesWords.length >= 2) {
            String firstWord = thisLinesWords[0];
            if (firstWord.equals("DROMIO")) {
              firstWord = firstWord + " " + thisLinesWords[1] + " " + thisLinesWords[2];
            }
            if (line.contains(".")) {
              //System.out.println("FirstWord: " + firstWord);
              updateCharacters(firstWord);
            }
        }
      }
    }
    System.out.println("Characters at the end of all: ");
    for (String key : charactersFreqMap.keySet()) {
      System.out.print(key + ": ");
      System.out.println(charactersFreqMap.get(key));
    }
  }

  public void charactersWithNumParts(int moreThan, int lessThan) {
    System.out.println("Character with num parts triggered");
    for (String key : charactersFreqMap.keySet()) {
      if (charactersFreqMap.get(key) <= lessThan && charactersFreqMap.get(key) >= moreThan) {
        System.out.println(key + " has " + charactersFreqMap.get(key) + " occurrences.");
      }
    }
  }

  public void wordsWithUsagesBetween(int moreThan, int lessThan) {
    System.out.println("Words with usages between " + moreThan + " and " + lessThan);
    for (String key : wordsFreqMap.keySet()) {
      if (wordsFreqMap.get(key) <= lessThan && wordsFreqMap.get(key) >= moreThan) {
        System.out.println(key + " has " + wordsFreqMap.get(key) + " usages.");
      }
    }
  }


}
