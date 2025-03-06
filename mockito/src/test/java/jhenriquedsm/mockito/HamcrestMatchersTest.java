package jhenriquedsm.mockito;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {
    @Test
    void testHamcrestMatchers() {
    	// Given / Arrange
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);

    	// When / Act & Then / Assert
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(100, 101));
        assertThat(scores, everyItem(greaterThan(98)));
        assertThat(scores, everyItem(lessThan(106)));

        // Check Strings
        assertThat("", is(emptyString()));
        assertThat(null, is(emptyOrNullString()));

        // Arrays
        Integer[] array = {1, 2, 3};
        assertThat(array, arrayWithSize(3));
        assertThat(array, arrayContaining(1, 2, 3));
        assertThat(array, arrayContainingInAnyOrder(3, 1, 2));
    }
}