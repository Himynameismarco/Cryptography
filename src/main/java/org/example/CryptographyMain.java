package main.java.org.example;


import java.io.IOException;

public class CryptographyMain {

  public static void main(String[] args) throws IOException {
    CaesarImplementation caesarImplementation = new CaesarImplementation();
    // caesarImplementation.makeAlphabetForKey(3);
    //System.out.println(caesarImplementation.makeCaesar(        "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15,false));
    //System.out.println(caesarImplementation.makeCaesarWithTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21, false));
    CaesearBreaking caesearBreaking = new CaesearBreaking();
    // System.out.println(caesarImplementation.makeCaesar("Hi my name is Marco!", 7,
    // true).toString());
    // System.out.println(caesearBreaking.findMostCommonWordLength("Hi my name is Marco!"));
    // System.out.println(caesearBreaking.findMostCommonWordLength(caesearBreaking.readFileToString("lib/romeo.txt")));
    System.out.println(caesearBreaking.getIndexOfMostFrequentLetter(caesearBreaking.readFileToString("lib/romeo.txt")));
    System.out.println(caesearBreaking.breakCaesarBasedOnFrequentLetters("ffffffff", 0));
  }
}

