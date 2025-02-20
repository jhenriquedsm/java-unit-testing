package jhenriquedsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {
    @Test
    void testSum() {
        SimpleMath simpleMath = new SimpleMath();
        Double firstNumber = 6.2D;
        Double secondNumber = 2D;

        Double actual = simpleMath.sum(firstNumber, secondNumber);
        Double expected = 8.2D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + " + " + secondNumber +
                        " did not produce " + expected + "!");
        Assertions.assertNotEquals(9.2, actual);
        Assertions.assertNotNull(actual);
    }
}