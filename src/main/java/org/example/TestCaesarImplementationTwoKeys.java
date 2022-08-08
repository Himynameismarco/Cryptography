package main.java.org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCaesarImplementationTwoKeys {

  @Test
  public void testMakeCaesar() {

    CaesarImplementationTwoKeys tester = new CaesarImplementationTwoKeys(23,2,false);
    String result = tester.makeCaesar("Just a test string with lots of eeeeeeeeeeeeeeeees");

    Assertions.assertEquals("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu", result);



  }

}
