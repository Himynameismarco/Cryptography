import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import main.java.org.example.GladLib;
import main.java.org.example.WordCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGladLib {

  @Test
  public void testGlabLib() {
    // GladLib test
    GladLib gladLib = new GladLib("res");
    gladLib.makeStory();

    System.out.println();
    System.out.println("Total number of words to be chosen from: " + gladLib.getTotalNumberOfWords());
    System.out.println("Total number of words chosen: " + gladLib.getTotalNumberOfWordsConsidered());
  }



}
