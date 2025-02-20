package jhenriquedsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {
    @Test
    void testSum() {
        SimpleMath simpleMath = new SimpleMath();
        Double actual = simpleMath.sum(6.2D, 2D);
        Double expected = 8.2D;

        Assertions.assertEquals(expected, actual, "6.2 + 2 did not produce 8.2");
        Assertions.assertNotEquals(9.2, actual);
        Assertions.assertNotNull(actual);
    }
}