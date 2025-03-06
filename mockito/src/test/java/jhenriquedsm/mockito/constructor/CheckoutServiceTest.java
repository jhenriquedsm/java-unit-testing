package jhenriquedsm.mockito.constructor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CheckoutServiceTest {
    @DisplayName("Mock Object Construction")
    @Test
    void testMockObjectConstruction() {
    	// Given / Arrange
        try(MockedConstruction<PaymentProcessor> mockedConstruction = Mockito.mockConstruction(
                PaymentProcessor.class,
                (mock, context) -> {
                    when(mock.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
                })) {

            // When / Act
            CheckoutService checkoutService = new CheckoutService();
            BigDecimal result = checkoutService.purchaseProduct("Ipad Pro", "42");

            // Then / Assert
            Assertions.assertEquals(BigDecimal.TEN, result);
            Assertions.assertEquals(1, mockedConstruction.constructed().size());
        }
    }
}