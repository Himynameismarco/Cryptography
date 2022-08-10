import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import main.java.org.example.WordCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestWordCounter {

  @Test
  public void testGetWordWithMostOccurrences() throws IOException {
    String path = "res/shakespeareFiles/hamlet.txt";
    WordCounter wordCounter = new WordCounter();
    HashMap<String, Integer> myWords = wordCounter.createListOfDifferentWords(path, true);
    String mostCommon = wordCounter.getWordWithMostOccurrences();
    System.out.println(mostCommon);

    Assertions.assertEquals("Word: the, length: 3, frequency: 1083", mostCommon);

    String path2 = "res/shakespeareFiles/likeit.txt";
    WordCounter wordCounter1 = new WordCounter();
    HashMap<String, Integer> myWords1 = wordCounter1.createListOfDifferentWords(path2, true);
    String mostCommon2 = wordCounter1.getWordWithMostOccurrences();
    System.out.println(mostCommon2);

    Assertions.assertEquals("Word: the, length: 3, frequency: 692", mostCommon2);



    String path3 = "res/errors.txt";
    WordCounter wordCounter2 = new WordCounter();
    HashMap<String, Integer> myWords2 = wordCounter2.createListOfDifferentWords(path3, true);
    System.out.println("Size of Word-List in errrors.txt: " + myWords2.size());
    String mostCommon3 = wordCounter2.getWordWithMostOccurrences();
    System.out.println(mostCommon3);



    /*
    WordCounter wordCounter2 = new WordCounter();
    wordCounter2.findAllCharacters("res/errors.txt");
    wordCounter2.charactersWithNumParts(10, 101);

     */

  }

  @Test
  public void testGetCharacterWithMostOccurrences() throws IOException {
    String path = "res/macbethSmall.txt";
    WordCounter wordCounter = new WordCounter();
    HashMap<String, Integer> myWords = wordCounter.createListOfDifferentWords(path, true);
    String mostCommon = wordCounter.getCharacterWithMostOccurrences();
    System.out.println(mostCommon);


    String path2 = "res/macbeth.txt";
    myWords = wordCounter.createListOfDifferentWords(path2, true);
    String mostCommon2 = wordCounter.getCharacterWithMostOccurrences();
    System.out.println(mostCommon2);

    //Assertions.assertEquals("Character: MACBETH, frequency: 5", mostCommon);
  }

  @Test
  public void testFindAllCharacters() throws IOException {
    WordCounter wordCounter = new WordCounter();
    wordCounter.findAllCharacters("res/likeit.txt");
  }

  @Test
  public void testCharactersWithNumParts() throws IOException {
    WordCounter wordCounter = new WordCounter();
    wordCounter.findAllCharacters("res/likeit.txt");
    wordCounter.charactersWithNumParts(10, 15);
  }



  }
