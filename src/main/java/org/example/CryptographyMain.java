package main.java.org.example;


public class CryptographyMain {

  public static void main(String[] args) {
    CaesarImplementation caesarImplementation = new CaesarImplementation();
    // caesarImplementation.makeAlphabetForKey(3);
    //caesarImplementation.makeCaesar(        "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15,false);
    caesarImplementation.makeCaesarWithTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21, false);
  }
}

