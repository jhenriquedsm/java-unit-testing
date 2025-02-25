package jhenriquedsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {
    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @Test
    @DisplayName("Test 6.2 + 2.0 = 8.2")
    void testSum_When_SixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo() {
        // AAA -> Arrange, Act, Assert
        // Given / Arrange
        SimpleMath simpleMath = new SimpleMath();
        Double firstNumber = 6.2D;
        Double secondNumber = 2D;
        Double expected = 8.2D;

        // When / Act
        Double actual = simpleMath.sum(firstNumber, secondNumber);

        // Then / Assert
        Assertions.assertEquals(expected, actual,
                () -> firstNumber + " + " + secondNumber +
                        " did not produce " + expected + "!");
        Assertions.assertNotEquals(9.2, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    @DisplayName("Test 20.0 - 10.0 = 10.0")
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
    @DisplayName("Test 5.0 * 5.0 = 25.0")
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
    @DisplayName("Test 25.0 / 5.0 = 5.0")
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

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @Disabled("TODO: We need still work on it!")
    @Test
    @DisplayName("Test Division by Zero")
    void testDivision_When_FirstNumberIsDivideByZero_ShouldThrowArithmeticException() {
        fail();
    }

    @Test
    @DisplayName("Test (12.5 + 17.5) / 2 = 15.0")
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

    @Test
    @DisplayName("Test squareRoot(25.0) = 5.0")
    void testSquareRoot() {
        SimpleMath simpleMath = new SimpleMath();
        Double number = 25D;

        Double actual = simpleMath.squareRoot(number);
        Double expected = 5D;

        Assertions.assertEquals(expected, actual,
                () -> "squareRoot(" + number + ") did not produce " + expected + "!"
        );
        Assertions.assertNotEquals(20D, expected);
    }
}