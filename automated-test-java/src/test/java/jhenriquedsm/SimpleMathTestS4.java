package jhenriquedsm;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTestS4 {
    SimpleMath simpleMath;

    @BeforeEach
    void beforeEachMethod() {
        System.out.println("Running @BeforeEach method!");
        simpleMath = new SimpleMath();
    }

    @DisplayName("Test Double Division [firsNumber, secondNumber, expected]")
    @ParameterizedTest
    // @MethodSource("testDivisionInputParameters")
    // @MethodSource()
    /*@CsvSource({
            "6.2, 2, 3.1",
            "71, 14, 5.07",
            "18.3, 3.1, 5.90"
    }) */
    @CsvFileSource(resources = "/testDivision.csv")
    void testDivision(Double firstNumber, Double secondNumber, Double expected) {
        Double actual = simpleMath.division(firstNumber, secondNumber);

        Assertions.assertEquals(expected, actual, 2D,
                () -> firstNumber + " / " + secondNumber +
                        " did not produce " + expected + "!"
        );
    }

    public static Stream<Arguments> testDivisionInputParameters() {
        return Stream.of(
                Arguments.of(6.2D, 2D, 3.1D),
                Arguments.of(71D, 14D, 5.07D),
                Arguments.of(18.3D, 3.1D, 5.90D)
        );
    }
    public static Stream<Arguments> testDivision() {
        return Stream.of(
                Arguments.of(6.2D, 2D, 3.1D),
                Arguments.of(71D, 14D, 5.07D),
                Arguments.of(18.3D, 3.1D, 5.90D)
        );
    }
}