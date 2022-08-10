package main.java.org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CodonCounter {

  HashMap<String, Integer> codonCounter = new HashMap<>();

  public HashMap<String, Integer> countCodons(int startAt, String dna) {
    codonCounter = new HashMap<>();
    String currentCodon = "";
    for (int i = startAt; i < dna.length(); i += 3) {
      if (i + 3 <= dna.length()) {
        currentCodon = dna.substring(i, i + 3);
        if (!codonCounter.containsKey(currentCodon)) {
          codonCounter.put(currentCodon, 1);
        } else {
          codonCounter.put(currentCodon, codonCounter.get(currentCodon) + 1);
        }
      }
    }
    return codonCounter;
  }

  public String getMostCommonCodon() {
    String maxCodon = "";
    int maxVal = 0;
    for (String key : codonCounter.keySet()) {
      if (codonCounter.get(key) > maxVal) {
        maxCodon = key;
        maxVal = codonCounter.get(key);
      }
    }
    System.out.println("Max Codon is " + maxCodon + ", occurring " + maxVal + " times.");
    return maxCodon;
  }

  public void printCodonsBetween(int moreThan, int lessThan) {
    for (String key : codonCounter.keySet()) {
      if (codonCounter.get(key) >= moreThan && codonCounter.get(key) <= lessThan) {
        System.out.println(key + ": " + codonCounter.get(key));
      }
    }
  }

}
