package main.java.org.example;

public class CaesarImplementationTwoKeys {

  private final String ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private final String ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
  private String encodedAlphabet1;
  private String encodedAlphabet2;
  private CaesarImplementationOneKey implKey1;
  private CaesarImplementationOneKey implKey2;

  /**
   * creates an instance for the caesar implementation for two keys.
   *
   * @param key1 - how far to the left or right uneven indizes should be moved in the alphabet.
   * @param key2 - how far to the left or right even inidizes should be moved in the alphabet.
   * @param leftShift - <code>true</code>, if left, else right.
   */
  public CaesarImplementationTwoKeys(int key1, int key2, boolean leftShift) {
    this.implKey1 = new CaesarImplementationOneKey(key1, leftShift);
    this.implKey2 = new CaesarImplementationOneKey(key2, leftShift);
    this.encodedAlphabet1 = implKey1.makeAlphabetForKey();
    this.encodedAlphabet2 = implKey2.makeAlphabetForKey();

  }

  /**
   * creates a message encoded with Caesar Cypher.
   *
   * @param message - the message.
   * @return encoded message.
   */
  public String makeCaesar(String message) {
    StringBuilder encryptedMessage = new StringBuilder();
    for (int i = 0; i < message.length(); i++) {
      Character c = message.charAt(i);
      String cAsString = c.toString();
      //System.out.println(cAsString);
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
        String encodedChar;
        if (i % 2 == 0) {
          encodedChar = String.valueOf(this.encodedAlphabet1.charAt(indexInAlphabet));
        } else {
          encodedChar = String.valueOf(this.encodedAlphabet2.charAt(indexInAlphabet));
        }
        //System.out.println("Encode " + cAsString + " to " + encodedChar);
        if (!upperCase) {
          encodedChar = encodedChar.toLowerCase();
        }
        encryptedMessage.append(encodedChar);
      }
    }
    System.out.println("Message '" + message + "' looks encrypted like this: ");
    System.out.println(encryptedMessage);
    return encryptedMessage.toString();
  }


}
