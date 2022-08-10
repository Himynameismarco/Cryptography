package main.java.org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Helper {

  /**
   * reads a given file to a String.
   *
   * @param pathAsString path to the string in the file strucutre.
   * @return the file as a String
   * @throws IOException you know that
   */
  public String readFileToString(String pathAsString) throws IOException {
    Path path = Path.of(pathAsString);
    String content = Files.readString(path, StandardCharsets.US_ASCII);
    StringBuilder stringBuilder = new StringBuilder(content);
    return content;
  }

}
