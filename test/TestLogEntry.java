import java.io.IOException;
import java.util.Date;
import main.java.org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestLogEntry {

  @Test
  public void testLogEntry() {
    LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
    System.out.println(le);

    LogEntry le2 = new LogEntry("1.2.300.4", new Date(), "example request 2", 300, 400);
    System.out.println(le2);
  }
}
