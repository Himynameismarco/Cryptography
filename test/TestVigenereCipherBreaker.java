import edu.duke.FileResource;
import java.io.IOException;
import java.util.HashSet;
import main.java.org.example.Helper;
import main.java.org.example.VigenereBreaker;
import main.java.org.example.VigenereCipher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestVigenereCipherBreaker {

  @Test
  public void testSlicer() throws IOException {


    VigenereBreaker vB = new VigenereBreaker();
    String zeroThLetters = vB.sliceString("Hi my name is Marco", 0, 4);
    System.out.println(zeroThLetters);

    Assertions.assertEquals("Hymsr", zeroThLetters);

    String letters1 = vB.sliceString("abcdefghijklm", 0, 3);

    Assertions.assertEquals("adgjm", letters1);

    String letters2 = vB.sliceString("abcdefghijklm", 1, 3);

    Assertions.assertEquals("behk", letters2);

    String letters3 = vB.sliceString("abcdefghijklm", 2, 3);

    Assertions.assertEquals("cfil", letters3);

    String letters4 = vB.sliceString("abcdefghijklm", 0, 4);

    Assertions.assertEquals("aeim", letters4);

    String letters5 = vB.sliceString("abcdefghijklm", 1, 4);

    Assertions.assertEquals("bfj", letters5);

    String letters6 = vB.sliceString("abcdefghijklm", 2, 4);

    Assertions.assertEquals("cgk", letters6);

    String letters7 = vB.sliceString("abcdefghijklm", 3, 4);

    Assertions.assertEquals("dhl", letters7);

    String letters8 = vB.sliceString("abcdefghijklm", 0, 5);

    Assertions.assertEquals("afk", letters8);

    String letters9 = vB.sliceString("abcdefghijklm", 1, 5);

    Assertions.assertEquals("bgl", letters9);

    String letters10 = vB.sliceString("abcdefghijklm", 2, 5);


    Assertions.assertEquals("chm", letters10);

    String letters11 = vB.sliceString("abcdefghijklm", 3, 5);


    Assertions.assertEquals("di", letters11);

    String letters12 = vB.sliceString("abcdefghijklm", 4, 5);


    Assertions.assertEquals("ej", letters12);





    int[] expectedKey = {5, 11, 20, 19, 4};
    Helper helper = new Helper();
    String athensPath = "res/vigenereTestData/athens_keyflute.txt";
    String athensKeyflute = helper.readFileToString(athensPath);
    int[] actualKey = vB.tryKeyLength(athensKeyflute, "flute".length(), 'e');

    for (int i = 0; i < actualKey.length; i++) {
      //System.out.println(actualKey[i]);
      Assertions.assertEquals(expectedKey[i], actualKey[i]);
    }

    vB.breakVigenere(athensKeyflute, 5, 'e');



    VigenereCipher vC = new VigenereCipher(new int[]{7, 4, 8, 9, 2, 11});
    String encryptedMessage = vC.encrypt("Hi, my name is Marco. I am an pretty amazing guy. A pretty and amazing guy. I love to live and live to love. I like to say, okay. I need more eeeeees. I like eeeeeeees. And eeeeeeees are what I like. Why do people don't like cucumber? This is what I ask!!!!");
    System.out.println(encryptedMessage);
    vB.breakVigenere(encryptedMessage, 6, 'e');



    //had to make my own file and change the UTF-8 encoding ('ä', 'ö', 'ü' and '-' have not been encoded in a way
    // that made it possible for the getKey-Method to get the right keys)
    String secret4 = new FileResource("res/vigenereTestData/secretmessage4_2").asString();
    VigenereBreaker vB3 = new VigenereBreaker();
    vB3.breakForAllLangs(secret4);

  }

}
