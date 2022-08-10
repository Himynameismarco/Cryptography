package main.java.org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCaesarImplementationOneKey {

  @Test
  public void testMakeCaesar() {
    CaesarImplementationOneKey tester = new CaesarImplementationOneKey(15, false);
    String result = tester.makeCaesar("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!").toString();

    Assertions.assertEquals("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!", result);

    CaesarImplementationOneKey tester2 = new CaesarImplementationOneKey(22, false);
    String result2 = tester2.makeCaesar("Hi my name is Marco! ").toString();

    Assertions.assertEquals("De iu jwia eo Iwnyk! ", result2);
  }

}
