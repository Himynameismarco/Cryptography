package main.java.org.example;

import com.sun.source.tree.BreakTree;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LogAnalyzer {

  private ArrayList<LogEntry> records;
  private HashMap<String, Integer> visitorCounts;

  public LogAnalyzer() {
    this.records = new ArrayList<>();
    this.visitorCounts = new HashMap<>();

  }

  public void readFile(String filename) throws FileNotFoundException {
    String source = "res/countingVisitsData/" + filename;
    File file = new File(source);
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        records.add(WebLogParser.parseEntry(line));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void printAll() {
    for (LogEntry le : records) {
      System.out.println(le);
    }
  }

  public int getCountOfDifferentIPsInGeneral() {
    ArrayList<String> uniqueIPs = new ArrayList<>();
    for (LogEntry le : records) {
      String currentIP = le.getIpAddress();
      if (!uniqueIPs.contains(currentIP)) {
        uniqueIPs.add(currentIP);
      }
    }
    return uniqueIPs.size();
  }

  public int getCountOfDifferentIPsInGeneralByHashMap() {
    HashMap<String, Integer> map = getHashMapOfWebsiteVisitors();
    int currentCount = 0;
    int maxCount = 0;
    String maxVisitor = "";
    for (String visitor : map.keySet()) {
      currentCount = map.get(visitor);
      if (currentCount > maxCount) {
        currentCount = maxCount;
        maxVisitor = visitor;
      }
    }
    System.out.println(maxVisitor + " is visitor with most visits, being: " + maxCount);
    return maxCount;

  }

  public int getCountOfDifferentIPsOnDay(String someday) {
    ArrayList<String> uniqueIPsOnDay = new ArrayList<>();
    for (LogEntry le : records) {
      String currentIP = le.getIpAddress();
      Date currentDate = le.getAccessTime();
      String currentDateAsString = String.valueOf(currentDate);
      currentDateAsString = currentDateAsString.substring(4, 10);
      if (currentDateAsString.equals(someday)) {
        //System.out.println(currentDate + " equals " + someday);
        if (!uniqueIPsOnDay.contains(currentIP)) {
          uniqueIPsOnDay.add(currentIP);
        }
      }
    }
    return uniqueIPsOnDay.size();
  }

  public HashMap<String, ArrayList<String>> getIPsForDays() {
    HashMap<String, ArrayList<String>> iPsForDays = new HashMap<>();
    for (LogEntry le : records) {
      ArrayList<String> dayList = new ArrayList<>();
      String currentIP = le.getIpAddress();
      Date currentDate = le.getAccessTime();
      String currentDateAsString = String.valueOf(currentDate);
      String temp = "";
      temp = currentDateAsString.substring(4, 10);
      currentDateAsString = temp + " " + currentDateAsString.substring(24);
      if (!iPsForDays.containsKey(currentIP)) {
        iPsForDays.put(currentIP, dayList);
        iPsForDays.get(currentIP).add(currentDateAsString);
      } else {
        if (!iPsForDays.get(currentIP).contains(currentDateAsString)) {
          iPsForDays.get(currentIP).add(currentDateAsString);
        }
      }
    }
    for (String key : iPsForDays.keySet()) {
      System.out.println(key + ": " + iPsForDays.get(key));
    }
    return iPsForDays;
  }

  //TODO
  public String iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String day) {
    int currentCount;
    int maxCount = 0;
    String maxIP = "";
    for (String ip : iPsForDays.keySet()) {
      currentCount = getVisitsOfVisitorOnDay(day, ip);
      System.out.println(currentCount);
      if (currentCount > maxCount) {
        maxCount = currentCount;
        maxIP = ip;
      }
    }
    System.out.println("Max IP on " + day + " is " + maxIP + " with count: " + maxCount);
    return maxIP;


  }

  public String getDayWithMostIpVisits() {
    HashMap<String, ArrayList<String>> ipsForDays = getIPsForDays();
    // use HashMap to count it.
    HashMap<String, Integer> countVisitsPerDay = new HashMap<>();
    for (String ip : ipsForDays.keySet()) {
      for (String day : ipsForDays.get(ip)) {
        if (!countVisitsPerDay.containsKey(day)) {
          countVisitsPerDay.put(day, 1);
        } else {
          countVisitsPerDay.put(day, countVisitsPerDay.get(day) + 1);
        }
      }
    }
    int max = 0;
    int currentCount;
    String maxDay = "";
    for (String day : countVisitsPerDay.keySet()) {
      currentCount = countVisitsPerDay.get(day);
      if (currentCount > max) {
        max = currentCount;
        maxDay = day;
      }
    }
    System.out.println(maxDay + " has most visits, being " + max);
    return maxDay;
  }

  public int getCountOfUniqueIPsInRange(int atLeast, int max) {
    int counter = 0;
    ArrayList<String> uniqueIPs = new ArrayList<>();
    for (LogEntry le : records) {
      String currentIP = le.getIpAddress();
      int currentNum = le.getStatusCode();
      if ((currentNum >= atLeast) && (currentNum <= max)) {
        if (!uniqueIPs.contains(currentIP)) {
          uniqueIPs.add(currentIP);
          System.out.println(le);
          counter++;
        }
      }
    }
    System.out.println(counter + " unique IPs have between " + atLeast + " and " + max + " status code.");
    return counter;
  }

  public void printAllLogsWithStatusCodeHigherThan(int num) {
    int counter = 0;
    for (LogEntry le : records) {
      int currentNum = le.getStatusCode();
      if (currentNum > num) {
        System.out.println(le);
        counter++;
      }
    }
    System.out.println("In total there are " + counter + " Log entries with status code "
        + "higher than " + num);
  }

  // and print
  public HashMap<String, Integer> getHashMapOfWebsiteVisitors() {
    for (LogEntry le : records) {
      String currentIP = le.getIpAddress();
      if (!visitorCounts.containsKey(currentIP)) {
        visitorCounts.put(currentIP, 1);
      } else {
        visitorCounts.put(currentIP, visitorCounts.get(currentIP) + 1);
      }
    }
    for (String key : visitorCounts.keySet()) {
      System.out.println(key + ": " + visitorCounts.get(key));
    }
    return visitorCounts;
  }

  private int getVisitsOfVisitorOnDay(String day, String ip) {
    int counter = 0;
    for (LogEntry le : records) {
      String currentIP = le.getIpAddress();
      Date currentDate = le.getAccessTime();
      String currentDateAsString = String.valueOf(currentDate);
      currentDateAsString = currentDateAsString.substring(4, 10);
      if (currentDateAsString.equals(day) && currentIP.equals(ip)) {
        counter++;
      }
    }
    System.out.println("On " + day + " " + ip + " has had " + counter + " visits. ");
    return counter;
  }




  public int getMaxNumberOfVisitsOfOneVisitor() {
    HashMap<String, Integer> visitors = getHashMapOfWebsiteVisitors();
    int max = 0;
    int current;
    String maxIp = "";
    for (String key : visitors.keySet()) {
      current = visitors.get(key);
      if (current > max) {
        max = current;
        maxIp = key;
      }
    }
    System.out.println(maxIp + " has max number of visits, being: " + max);
    return max;
  }

}
