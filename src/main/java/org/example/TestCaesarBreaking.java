package main.java.org.example;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCaesarBreaking {

  @Test
  public void testCaesarBreakingTwoKeys() throws IOException {

    CaesearBreaking breaker = new CaesearBreaking();
    String result = breaker.breakCaesarForTwoKeysUsingMostFrequentLetter("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu");
    Assertions.assertEquals("Just a test string with lots of eeeeeeeeeeeeeeeees", result);

    String result2 = breaker.readFileToString("res/assignment.txt");
    result2 = breaker.breakCaesarForTwoKeysUsingMostFrequentLetter(result2);
    String solution = breaker.readFileToString("res/assignmentSolution.txt");
    Assertions.assertEquals(solution, result2);


  }

}
