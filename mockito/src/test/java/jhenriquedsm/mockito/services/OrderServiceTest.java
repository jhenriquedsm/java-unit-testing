package jhenriquedsm.mockito.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderServiceTest {

    private final OrderService orderService = new OrderService();
    private final UUID defaultUUID = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2025, 3, 6, 16, 30);

    @DisplayName("Should Include Random Order Id When No Order Id Exists")
    @Test
    void testShouldIncludeRandomOrderId_When_NoOrderIdExists() {
    	// Given / Arrange
        try(MockedStatic<UUID> mockedStatic = mockStatic(UUID.class)) {
            mockedStatic.when(UUID::randomUUID).thenReturn(defaultUUID);

            // When / Act
            Order result = orderService.createOrder("Macbook Pro", 2L, null);

            // Then / Assert
            Assertions.assertEquals(defaultUUID.toString(), result.getId());
        }
    }

    @DisplayName("Should Include Current Time When Create a New Order")
    @Test
    void testShouldIncludeCurrentTime_When_CreateANewOrder() {
        // Given / Arrange
        try(MockedStatic<LocalDateTime> localDateTimeMockedStatic = mockStatic(LocalDateTime.class)) {
            localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            // When / Act
            Order result = orderService.createOrder("Macbook Pro", 2L, null);

            // Then / Assert
            Assertions.assertEquals(defaultLocalDateTime, result.getCreationDate());
        }
    }
}