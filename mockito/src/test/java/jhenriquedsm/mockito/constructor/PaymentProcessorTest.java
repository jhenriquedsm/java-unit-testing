package jhenriquedsm.mockito.constructor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PaymentProcessorTest {

    @DisplayName("Mock Object Construction")
    @Test
    void testMockObjectConstruction() {
        try (MockedConstruction<PaymentProcessor> mockedConstruction = mockConstruction(PaymentProcessor.class)) {
            // Given / Arrange
            PaymentProcessor paymentProcessor = new PaymentProcessor();

            // When / Act
            when(paymentProcessor.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

            // Then / Assert
            assertEquals(BigDecimal.TEN, paymentProcessor.chargeCustomer("42", BigDecimal.valueOf(42)));
        }
    }

    @DisplayName("Mock Different Object Construction")
    @Test
    void tesMockDifferentObjectConstruction() {
        try (MockedConstruction<PaymentProcessor> mocked = Mockito.mockConstruction(PaymentProcessor.class)) {
            // Given / Arrange
            PaymentProcessor firstInstance = new PaymentProcessor("PayPal", BigDecimal.TEN);
            PaymentProcessor secondInstance = new PaymentProcessor(true, "PayPal", BigDecimal.TEN);

            // When / Act
            when(firstInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
            when(secondInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

            // Then / Assert
            assertEquals(BigDecimal.TEN, firstInstance.chargeCustomer("42", BigDecimal.valueOf(42)));
            assertEquals(BigDecimal.TEN, secondInstance.chargeCustomer("42", BigDecimal.valueOf(42)));
            assertEquals(2, mocked.constructed().size());
        }
    }
}
