package jhenriquedsm;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoRepeatedTest {
    SimpleMath simpleMath;

    @BeforeEach
    void beforeEachMethod() {
        System.out.println("Running @BeforeEach method!");
        simpleMath = new SimpleMath();
    }

    @RepeatedTest(value = 3, name = "{displayName}. Repetition {currentRepetition} of {totalRepetitions}!")
    @DisplayName("Test Division by Zero")
    void testDivision_When_FirstNumberIsDivideByZero_ShouldThrowArithmeticException(RepetitionInfo repetitionInfo, TestInfo testInfo) {
        System.out.println("Repetition Nº " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        System.out.println("Running " + testInfo.getTestMethod().get().getName());
        // Given
        Double firstNumber = 6.2D;
        Double secondNumber = 0D;

        var expectedMessage = "Impossible to divide by 0";

        // When & Then
        ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
            simpleMath.division(firstNumber, secondNumber);
        }, () -> "Division by 0 should throw an ArithmeticException");

        assertEquals(expectedMessage, actual.getMessage(), () -> "Unexpected exception message!");
    }
}