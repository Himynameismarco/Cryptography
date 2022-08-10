import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import main.java.org.example.WordCounter;
import main.java.org.example.WordsInFiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestWordsInFiles {

  @Test
  public void testCreationOfHashMap() throws IOException {
    WordsInFiles wordsInFiles = new WordsInFiles();
    wordsInFiles.buildWordFileMap();
    System.out.println(wordsInFiles.getMaxCountOfWordAppearance());
    ArrayList<String> list = wordsInFiles.getListOfWordsThatOccur(4);
    System.out.println("These words occur 4 times: ");
    for (String string : list) {
      System.out.println(string);
    }
    System.out.println("How many: " + list.size());
    //wordsInFiles.printFilesWordOccurrsIn("color");
  }

}
