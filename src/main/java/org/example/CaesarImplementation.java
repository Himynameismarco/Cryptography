package main.java.org.example;

public class CaesarImplementation {

  private final String ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private final String ALPHABET_LOWER = "abcdefghijklmnopqrstuvwyyz";

  /**
   * creates a message encoded with Caesar Cypher.
   *
   * @param message - the message.
   * @param key - how far to the left or right it should be moved in the alphabet.
   * @param leftShift - <code>true</code>, if left, else right.
   * @return encoded message.
   */
  public StringBuilder makeCaesar(String message, int key, boolean leftShift) {
    StringBuilder encryptedMessage = new StringBuilder();
    String encodedAlphabet = makeAlphabetForKey(key, leftShift);
    for (int i = 0; i < message.length(); i++) {
      Character c = message.charAt(i);
      String cAsString = c.toString();
      System.out.println(cAsString);
      int indexInAlphabet;
      boolean upperCase;
      if (Character.isUpperCase(c)) {
        indexInAlphabet = ALPHABET_UPPER.indexOf(cAsString);
        upperCase = true;
      } else {
        upperCase = false;
        indexInAlphabet = ALPHABET_LOWER.indexOf(cAsString);
      }
      System.out.println("index for " + cAsString + " in Alphabet: " + indexInAlphabet);
      if (indexInAlphabet < 0) {
        encryptedMessage.append(cAsString);
      } else {
        String encodedChar = String.valueOf(encodedAlphabet.charAt(indexInAlphabet));
        System.out.println("Encode " + cAsString + " to " + encodedChar);
        if (!upperCase) {
          encodedChar = encodedChar.toLowerCase();
        }
        encryptedMessage.append(encodedChar);
      }
    }
    System.out.println("Message '" + message + "' looks encrypted like this: ");
    System.out.println(encryptedMessage);
    return encryptedMessage;
  }

  /**
   * creates a message encoded with Caesar Cypher.
   *
   * @param message - the message.
   * @param key1 - how far to the left or right uneven indizes should be moved in the alphabet.
   * @param key2 - how far to the left or right even inidizes should be moved in the alphabet.
   * @param leftShift - <code>true</code>, if left, else right.
   * @return encoded message.
   */
  public StringBuilder makeCaesarWithTwoKeys(String message, int key1, int key2, boolean leftShift) {
    StringBuilder encryptedMessage = new StringBuilder();
    String encodedAlphabet1 = makeAlphabetForKey(key1, leftShift);
    String encodedAlphabet2 = makeAlphabetForKey(key2, leftShift);
    for (int i = 0; i < message.length(); i++) {
      Character c = message.charAt(i);
      String cAsString = c.toString();
      System.out.println(cAsString);
      int indexInAlphabet;
      boolean upperCase;
      if (Character.isUpperCase(c)) {
        indexInAlphabet = ALPHABET_UPPER.indexOf(cAsString);
        upperCase = true;
      } else {
        upperCase = false;
        indexInAlphabet = ALPHABET_LOWER.indexOf(cAsString);
      }
      System.out.println("index for " + cAsString + " in Alphabet: " + indexInAlphabet);
      if (indexInAlphabet < 0) {
        encryptedMessage.append(cAsString);
      } else {
        String encodedChar;
        if (i % 2 == 0) {
          encodedChar = String.valueOf(encodedAlphabet1.charAt(indexInAlphabet));
        } else {
          encodedChar = String.valueOf(encodedAlphabet2.charAt(indexInAlphabet));
        }
        System.out.println("Encode " + cAsString + " to " + encodedChar);
        if (!upperCase) {
          encodedChar = encodedChar.toLowerCase();
        }
        encryptedMessage.append(encodedChar);
      }
    }
    System.out.println("Message '" + message + "' looks encrypted like this: ");
    System.out.println(encryptedMessage);
    return encryptedMessage;
  }



  /**
   * creates the alphabet for the respective key and whether it is meant to be shifted left
   * or right.
   *
   * @param key - how far to the left or right it should be moved in the alphabet.
   * @param leftShift - <code>true</code>, if left, else right.
   * @return - the alphabet used for encryption.
   */
  public String makeAlphabetForKey(int key, boolean leftShift) {
    String encodedAlphabet = "";
    for (int i = 0; i < ALPHABET_UPPER.length(); i++) {
      int index;
      System.out.println("i: " + i);
      if (leftShift) {
        index = i - key;
        if (index < 0) {
          index = 26 + index;
        }
      } else  {
        index = i + key;
        if (index > 25) {
          index = index - 26;
        }
      }
      //System.out.println("index: " + index + " in Alphabet has char: " + ALPHABET_UPPER.charAt(index));
      encodedAlphabet = encodedAlphabet + String.valueOf(ALPHABET_UPPER.charAt(index));
    }
    System.out.println("Encoded Alphabet with key " + key + " and leftShift is " + leftShift + ".");
    System.out.println(encodedAlphabet);
    return encodedAlphabet;

  }

  public String alternativeMakeCaesar(String message, int key) {
    return "";
  }

}
