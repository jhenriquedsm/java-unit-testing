package jhenriquedsm.business;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ListWithBDDTest {
    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
    	// Given / Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10);

    	// When / Act & Then / Assert
        assertThat(list.size(), is(10));
    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given / Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10).willReturn(20);

        // When / Act & Then / Assert
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(20));
        assertThat(list.size(), is(20));
    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnJH() {
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(0)).willReturn("JH");

        // When / Act & Then / Assert
        assertThat(list.get(0), is("JH"));
        Assertions.assertNull(list.get(1));
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnJH() {
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willReturn("JH");

        // When / Act & Then / Assert
        assertThat(list.get(anyInt()), is("JH"));
    }

    @Test
    void testMockingList_When_ThrowsAnException() {
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willThrow(new RuntimeException("Foo Bar!!"));

        // When / Act & Then / Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> {
            list.get(anyInt());
            }, () -> "Should have throw an RuntimeException");
    }
}