package jhenriquedsm.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ListTest {
    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
    	// Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10);

    	// When / Act & Then / Assert
        Assertions.assertEquals(10, list.size());
    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);

        // When / Act & Then / Assert
        Assertions.assertEquals(10, list.size());
        Assertions.assertEquals(20, list.size());
        Assertions.assertEquals(20, list.size());
    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnJH() {
        // Given / Arrange
        var list = mock(List.class);
        when(list.get(0)).thenReturn("JH");

        // When / Act & Then / Assert
        Assertions.assertEquals("JH", list.get(0));
        Assertions.assertNull(list.get(1));
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnJH() {
        // Given / Arrange
        var list = mock(List.class);
        when(list.get(anyInt())).thenReturn("JH");

        // When / Act & Then / Assert
        Assertions.assertEquals("JH", list.get(anyInt()));
    }
}