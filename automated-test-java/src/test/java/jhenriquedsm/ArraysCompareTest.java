package jhenriquedsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ArraysCompareTest {

    @Test
    void test() {
        int[] numbers = {25, 8, 21, 32, 3};
        int[] expectedArray = {3, 8, 21, 25, 32};

        Arrays.sort(numbers);

        Assertions.assertArrayEquals(numbers, expectedArray);
    }
}