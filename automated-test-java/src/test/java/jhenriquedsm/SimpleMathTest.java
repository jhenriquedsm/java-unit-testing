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

    @Test
    void testSubtraction() {
        SimpleMath simpleMath = new SimpleMath();
        Double firstNumber = 20D;
        Double secondNumber = 10D;

        Double actual = simpleMath.subtraction(firstNumber, secondNumber);
        Double expected = 10D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + " - " + secondNumber +
                        " did not produce " + expected + "!"
        );
        Assertions.assertNotEquals(9.9, expected);
    }

    @Test
    void testMultiplication() {
        SimpleMath simpleMath = new SimpleMath();
        Double firstNumber = 5D;
        Double secondNumber = 5D;

        Double actual = simpleMath.multiplication(firstNumber, secondNumber);
        Double expected = 25D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + " * " + secondNumber +
                        " did not produce " + expected + "!"
        );
        Assertions.assertNotEquals(10D, expected);
    }

    @Test
    void testDivision() {
        SimpleMath simpleMath = new SimpleMath();
        Double firstNumber = 25D;
        Double secondNumber = 5D;

        Double actual = simpleMath.division(firstNumber, secondNumber);
        Double expected = 5D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + " / " + secondNumber +
                        " did not produce " + expected + "!"
        );
        Assertions.assertNotEquals(4D, expected);
    }

    @Test
    void testMean() {
        SimpleMath simpleMath = new SimpleMath();
        Double firstNumber = 12.5;
        Double secondNumber = 17.5;

        Double actual = simpleMath.mean(firstNumber, secondNumber);
        Double expected = 15D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + " + " + secondNumber +
                        " / 2 did not produce " + expected + "!"
        );
        Assertions.assertNotEquals(10D, expected);
    }
}