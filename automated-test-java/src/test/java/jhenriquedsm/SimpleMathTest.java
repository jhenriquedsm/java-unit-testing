package jhenriquedsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {
    @Test
    void testSum() {
        SimpleMath simpleMath = new SimpleMath();
        Double actual = simpleMath.sum(6.2D, 2D);
        Double expected = 8.2D;

        Assertions.assertEquals(expected, actual);
    }
}