package main.java.org.example;

import java.util.ArrayList;

public class CasearBreaking {

  // Eyeball-Method
  public ArrayList<StringBuilder> giveAllPotentialCombinations(String encryptedMessage) {
    CaesarImplementation caesarImplementation = new CaesarImplementation();
    StringBuilder currentVersion = new StringBuilder();
    ArrayList<StringBuilder> allCombinations = new ArrayList<>();
    for (int i = 1; i < 27; i++) {
      currentVersion.append("Key ").append(i).append(": ");
      currentVersion.append(caesarImplementation.makeCaesar(encryptedMessage, i, true));
      System.out.println(currentVersion);
      allCombinations.add(currentVersion);
      currentVersion.setLength(0);
    }
    return allCombinations;
  }

}
