import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import main.java.org.example.LogAnalyzer;
import org.junit.jupiter.api.Test;

public class TestLogAnalyzer {

  @Test
  public void testLogAnalyzer() throws FileNotFoundException {


    LogAnalyzer logAnalyzer = new LogAnalyzer();
    logAnalyzer.readFile("weblog2_log");

    logAnalyzer.printAll();
    System.out.println("There are " + logAnalyzer.getCountOfDifferentIPsInGeneral() + " IPs.");
    System.out.println(logAnalyzer.getCountOfDifferentIPsOnDay("Sep 24"));
    logAnalyzer.getCountOfUniqueIPsInRange(200, 299);
    HashMap<String, ArrayList<String>> iPsForDays = logAnalyzer.getIPsForDays();
    System.out.println(logAnalyzer.getMaxNumberOfVisitsOfOneVisitor());
    System.out.println(logAnalyzer.getDayWithMostIpVisits());
    System.out.println(logAnalyzer.iPsWithMostVisitsOnDay(iPsForDays, "Sep 29"));


    /*
    System.out.println("Higher than num Log entries: ");
    logAnalyzer.printAllLogsWithStatusCodeHigherThan(400);
    String day = "Mar 24";
    System.out.println("There are " + logAnalyzer.getCountOfDifferentIPsOnDay(day) + " Ips on day " + day);
    logAnalyzer.getCountOfUniqueIPsInRange(200, 299);


    System.out.println(logAnalyzer.getHashMapOfWebsiteVisitors());


     */


    /*
    LogAnalyzer logAnalyzer2 = new LogAnalyzer();
    logAnalyzer2.readFile("weblog1_log");
    HashMap<String, ArrayList<String>> iPsForDays = logAnalyzer2.getIPsForDays();
    String day = "Mar 17";
    System.out.println(logAnalyzer2.iPsWithMostVisitsOnDay(iPsForDays, day));

     */
  }

}
