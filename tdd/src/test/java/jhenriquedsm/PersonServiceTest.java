package jhenriquedsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {
    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
    	// Given / Arrange
        IPersonService service = new PersonService();
        Person person = new Person(
                "José",
                "Henrique",
                "josehenrique@email.com",
                "Brasília - DF",
                "Male"
        );

    	// When / Act
        Person actual = service.createPerson(person);

    	// Then / Assert
        Assertions.assertNotNull(actual, () -> "The createPerson() should not have returned null!");
    }
}