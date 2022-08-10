package main.java.org.example;


import java.io.IOException;

public class CryptographyMain {

  public static void main(String[] args) throws IOException {

    // encrypting a message with one key
    CaesarImplementationOneKey impl1Key = new CaesarImplementationOneKey(15, false);
    String encrytped = impl1Key.makeCaesar("Can you imagine life WITHOUT the internet AND computers in your pocket?").toString();
    System.out.println(encrytped);

    // encrypting a message with two keys
    CaesarImplementationTwoKeys impl2key = new CaesarImplementationTwoKeys(21, 8, false);
    String encrypted2 = impl2key.makeCaesar("Can you imagine life WITHOUT the internet AND computers in your pocket?");
    System.out.println(encrypted2);

    // breaking the message with known keys
    CaesearBreaking breaking = new CaesearBreaking();
    String decrypted =
        breaking.breakCaesarKnowingKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 14, 24, true);
    System.out.println(decrypted);

    // breaking the message based on most frequent
    String decrypted2 = breaking.breakCaesarForTwoKeysUsingMostFrequentLetter("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
    System.out.println(decrypted2);

    // breaking the message based on most frequent for file to be read in
    Helper helper = new Helper();
    String encrypted3 = helper.readFileToString("res/mysteryTwoKeys.txt");
    String decrypted3 = breaking.breakCaesarForTwoKeysUsingMostFrequentLetter(encrypted3);
    System.out.println(decrypted3);
  }
}

