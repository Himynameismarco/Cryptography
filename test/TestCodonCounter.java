import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import main.java.org.example.CodonCounter;
import main.java.org.example.WordCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCodonCounter {

  @Test
  public void testCountCodons() {
    CodonCounter codonCounter = new CodonCounter();
    HashMap<String, Integer> test = codonCounter.countCodons(0, "CGTTCAAGTTCAA");
    codonCounter.printCodonsBetween(1, 5);
    String mostCommon = codonCounter.getMostCommonCodon();

    Assertions.assertEquals("TCA", mostCommon);

    System.out.println("Next.");

    HashMap<String, Integer> test2 = codonCounter.countCodons(1, "CGTTCAAGTTCAA");
    codonCounter.printCodonsBetween(1, 5);

    System.out.println("Next.");

    HashMap<String, Integer> test3 = codonCounter.countCodons(2, "CGTTCAAGTTCAA");
    codonCounter.printCodonsBetween(1, 5);
    String mostCommon2 = codonCounter.getMostCommonCodon();

    Assertions.assertEquals("TTC", mostCommon2);

    System.out.println("Next.");

    HashMap<String, Integer> test4 =
        codonCounter.countCodons(
            1,
            "ATTAATACTTTGTTTAACAGTAATTATTCAACTATTAAATATTTAAATAATTAAGTTATTAAACTATTAAGTACAGGGCCTGTATCTCTGATGCTGAACTATGATGTGTGACTTAAGCCCCCAAATACATCATGTTATTTGGATCCAAGGTGCTGCACAGAACGCTGACCCTCTCTAAGAGCTGGGTATTACT");
    codonCounter.printCodonsBetween(5, 7);
    String mostCommon3 = codonCounter.getMostCommonCodon();

    System.out.println("Next.");

    HashMap<String, Integer> test5 = codonCounter.countCodons(0, "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC");
    System.out.println("Size of test5: " + test5.size());
    codonCounter.printCodonsBetween(7, 7);
    String mostCommon4 = codonCounter.getMostCommonCodon();
  }

}
