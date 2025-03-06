package jhenriquedsm.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

// mocks parciais
public class SpyTest {
    @Test
    void testSpyV1() {
    	// Given / Arrange
        List<String> mockArrayList = spy(ArrayList.class);

    	// When / Act & Then / Assert
        Assertions.assertEquals(0, mockArrayList.size());

        when(mockArrayList.size()).thenReturn(5);
        mockArrayList.add("Foo Bar");
        Assertions.assertEquals(5, mockArrayList.size());
    }

    @Test
    void testSpyV2() {
        // Given / Arrange
        List<String> spyArrayList = spy(ArrayList.class);

        // When / Act & Then / Assert
        Assertions.assertEquals(0, spyArrayList.size());

        spyArrayList.add("Foo Bar");
        Assertions.assertEquals(1, spyArrayList.size());

        spyArrayList.remove("Foo Bar");
        Assertions.assertEquals(0, spyArrayList.size());
    }

    @Test
    void testSpyV3() {
        // Given / Arrange
        List<String> spyArrayList = spy(ArrayList.class);

        // When / Act & Then / Assert
        Assertions.assertEquals(0, spyArrayList.size());
        when(spyArrayList.size()).thenReturn(5);
        Assertions.assertEquals(5, spyArrayList.size());
    }

    @Test
    void testSpyV4() {
        // Given / Arrange
        List<String> spyArrayList = spy(ArrayList.class);
        spyArrayList.add("Foo Bar");

        // When / Act & Then / Assert
        verify(spyArrayList).add("Foo Bar");
        verify(spyArrayList, never()).remove("Foo Bar");
        verify(spyArrayList, never()).remove(anyString());
        verify(spyArrayList, never()).clear();
    }
}