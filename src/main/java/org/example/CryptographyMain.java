package main.java.org.example;


import java.io.IOException;

public class CryptographyMain {

  public static void main(String[] args) throws IOException {
    CaesarImplementationOneKey caesarImplementationOneKey = new CaesarImplementationOneKey(7, true);
    // caesarImplementation.makeAlphabetForKey(3);
    //System.out.println(caesarImplementation.makeCaesar(        "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15,false));
    //System.out.println(caesarImplementation.makeCaesarWithTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21, false));
    CaesearBreaking caesearBreaking = new CaesearBreaking();
    // System.out.println(caesarImplementation.makeCaesar("Hi my name is Marco!", 7,
    // true).toString());
    // System.out.println(caesearBreaking.findMostCommonWordLength("Hi my name is Marco!"));
    // System.out.println(caesearBreaking.findMostCommonWordLength(caesearBreaking.readFileToString("lib/lostWords.txt")));
    // System.out.println(caesearBreaking.getIndexOfMostFrequentLetter(caesearBreaking.readFileToString("lib/romeo.txt")));
    // System.out.println(caesearBreaking.breakCaesarBasedOnFrequentLetters("ffffffff", 0));
    // System.out.println(caesearBreaking.breakCaesarForTwoKeysUsingMostFrequentLetter("Gwpv c vbuq
    // pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
    // System.out.println(caesearBreaking.halfOfString("Lola ist sehr lustig ma lsek", 0));
    // System.out.println(
    //   caesearBreaking.breakCaesarKnowingKeys("Top ncmy qkff vi vguv vbg ycpx", 2, 20));
    // String twoKeys = caesarImplementation.makeCaesarWithTwoKeys("Hi my name is Marco and I love
    // eeeeeeeeeeeeeeeeeeeeeeeeeees so mache that I just can't live without them. EEeeeeee!", 7 ,
    // 22, false).toString();
    // System.out.println(caesearBreaking.breakCaesarForTwoKeysUsingMostFrequentLetter("Akag tjw
    // Xibhr awoa aoee xakex znxag xwko"));
    // System.out.println(caesearBreaking.breakCaesarForTwoKeysUsingMostFrequentLetter(twoKeys));
    String assignment = caesearBreaking.readFileToString("lib/assignment.txt");
    System.out.println(caesearBreaking.breakCaesarForTwoKeysUsingMostFrequentLetter(assignment));

    //System.out.println(caesarImplementationOneKey.makeCaesar("Hi my name is Marco!").toString());
  }
}

