package main.java.org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;

public class WordsInFiles {

  HashMap<String, ArrayList<String>> wordsInFile = new HashMap<>();

  private void addWordsFromFile(File f) throws IOException {
    System.out.println("Start adding words from file: ");
    System.out.println(f.getName());
    String toBeSplitted = FileUtils.readFileToString(f);
    String[] allWords = toBeSplitted.split("\\s+");
    for (String word : allWords) {
      if (!wordsInFile.containsKey(word)) {
        //System.out.println("word: " + word);
        ArrayList<String> fileArrayList = new ArrayList<>();
        fileArrayList.add(f.getName());
        wordsInFile.put(word, fileArrayList);
      } else {
        if (!wordsInFile.get(word).contains(f.getName())) {
          wordsInFile.get(word).add(f.getName());
        }
      }
    }
    for (String key : wordsInFile.keySet()) {
      System.out.print(key + ": ");
      for (String filename : wordsInFile.get(key)) {
        System.out.println(filename + ", ");
      }
      System.out.println();
    }
  }

  public void buildWordFileMap() throws IOException {
    wordsInFile = new HashMap<>();
    File folder = new File("res/sevenMore");
    File[] listOfFiles = folder.listFiles();

    for (File file : listOfFiles) {
      if (file.isFile()) {
        addWordsFromFile(file);
      }
    }
  }

  public int getMaxCountOfWordAppearance() {
    int current;
    int max = 0;
    String maxWord = "";
    for (String key : wordsInFile.keySet()) {
      current = wordsInFile.get(key).size();
      if (current > max) {
        max = current;
        maxWord = key;
      }
    }
    return max;
  }

  public ArrayList<String> getListOfWordsThatOccur(int times) {
    System.out.println("WordsInFile before: " + wordsInFile.size());
    ArrayList<String> result = new ArrayList<>();
    for (String key : wordsInFile.keySet()) {
      if (wordsInFile.get(key).size() == times) {
        if (!result.contains(key)) {
          result.add(key);
        }
      }
    }
    return result;
  }

  public void printFilesWordOccurrsIn(String word) {
    for (String key : wordsInFile.keySet()) {
      if (key.equals(word)) {
        System.out.println(key + " appears in: ");
        for (String filename : wordsInFile.get(key)) {
          System.out.println(filename);
        }
      }
    }
  }


}
