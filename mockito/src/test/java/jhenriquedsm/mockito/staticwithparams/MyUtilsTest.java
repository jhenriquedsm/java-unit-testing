package jhenriquedsm.mockito.staticwithparams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;

public class MyUtilsTest {
    @DisplayName("Should Mock Static Method With Params")
    @Test
    void testShouldMockStaticMethodWithParams() {
    	// Given / Arrange
        try(MockedStatic<MyUtils> mockedStatic = Mockito.mockStatic(MyUtils.class)) {
            mockedStatic.when(
                    () -> MyUtils.getWelcomeMessage(
                            eq("JH"),
                            anyBoolean())).thenReturn("Hi, JH");

            // When / Act
            String result = MyUtils.getWelcomeMessage("JH", false);

            // Then / Assert
            Assertions.assertEquals("Hi, JH", result);
        }
    }
}