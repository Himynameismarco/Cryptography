package main.java.org.example;

public class CaesarImplementationOneKey {

  private final String ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private final String ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
  private String SHIFTED_ALPHABET;
  private boolean leftShift;
  private int key;

  /**
   * Creates an instance of the CaesarImplementation with one key.
   *
   * @param key - how far to the left or right it should be moved in the alphabet.
   * @param leftShift - <code>true</code>, if left, else right.
   */
  public CaesarImplementationOneKey(int key, boolean leftShift) {
    this.leftShift = leftShift;
    this.key = key;
    this.SHIFTED_ALPHABET = makeAlphabetForKey();

  }

  /**
   * creates a message encoded with Caesar Cypher.
   *
   * @param message - the message.
   * @return encoded message.
   */
  public StringBuilder makeCaesar(String message) {
    StringBuilder encryptedMessage = new StringBuilder();
    String encodedAlphabet = makeAlphabetForKey();
    for (int i = 0; i < message.length(); i++) {
      Character c = message.charAt(i);
      String cAsString = c.toString();
      int indexInAlphabet;
      boolean upperCase;
      if (Character.isUpperCase(c)) {
        indexInAlphabet = ALPHABET_UPPER.indexOf(cAsString);
        upperCase = true;
      } else {
        upperCase = false;
        indexInAlphabet = ALPHABET_LOWER.indexOf(cAsString);
      }
      //System.out.println("index for " + cAsString + " in Alphabet: " + indexInAlphabet);
      if (indexInAlphabet < 0) {
        encryptedMessage.append(cAsString);
      } else {
        String encodedChar = String.valueOf(encodedAlphabet.charAt(indexInAlphabet));
        //System.out.println("Encode " + cAsString + " to " + encodedChar);
        if (!upperCase) {
          encodedChar = encodedChar.toLowerCase();
        }
        encryptedMessage.append(encodedChar);
      }
    }
    //System.out.println("Message '" + message + "' looks encrypted like this: ");
    //System.out.println(encryptedMessage);
    return encryptedMessage;
  }


  /**
   * creates the alphabet for the respective key and whether it is meant to be shifted left
   * or right.
   *
   * @return - the alphabet used for encryption.
   */
  String makeAlphabetForKey() {
    String encodedAlphabet = "";
    for (int i = 0; i < ALPHABET_UPPER.length(); i++) {
      int index;
      //System.out.println("i: " + i);
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
    //System.out.println("Encoded Alphabet with key " + key + " and leftShift is " + leftShift + ".");
    System.out.println(encodedAlphabet);
    return encodedAlphabet;

  }

  public String getALPHABET_UPPER() {
    return ALPHABET_UPPER;
  }

  public String getALPHABET_LOWER() {
    return ALPHABET_LOWER;
  }
}
