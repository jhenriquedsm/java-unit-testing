package jhenriquedsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {
    Person person;

    @BeforeEach
    void setup() {
        person = new Person(
                "José",
                "Henrique",
                "josehenrique@email.com",
                "Brasília - DF",
                "Male"
        );
    }

    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
    	// Given / Arrange
        IPersonService service = new PersonService();

    	// When / Act
        Person actual = service.createPerson(person);

    	// Then / Assert
        Assertions.assertNotNull(actual, () -> "The createPerson() should not have returned null!");
    }

    @DisplayName("When Create a Person with Success Should Contains First Name In Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsFirstNameInReturnedPersonObject() {
        // Given / Arrange
        IPersonService service = new PersonService();

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertEquals(person.getFirstName(), actual.getFirstName(), () -> "The firstName's is different!");
    }
}