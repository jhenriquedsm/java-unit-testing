package jhenriquedsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class PersonServiceTest {
    IPersonService service;
    Person person;

    @BeforeEach
    void setup() {
        service = new PersonService();
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

    	// When / Act
        Person actual = service.createPerson(person);

    	// Then / Assert
        Assertions.assertNotNull(actual, () -> "The createPerson() should not have returned null!");
    }

    @DisplayName("When Create a Person with Success Should Contains First Name In Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsFirstNameInReturnedPersonObject() {
        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertEquals(person.getFirstName(), actual.getFirstName(), () -> "The firstName's is different!");
    }

    @DisplayName("When Create a Person with Success Should Contains Second Name In Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsSecondNameInReturnedPersonObject() {
        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertEquals(person.getSecondName(), actual.getSecondName(), () -> "The secondName's is different!");
    }

    @DisplayName("When Create a Person with Success Should Contains Email Not Null In Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsEmailNotNullInReturnedPersonObject() {
        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertNotNull(actual.getEmail(), () -> "The email should not have null!");
    }

    @DisplayName("When Create a Person with Success Should Contains Address In Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsAddressInReturnedPersonObject() {
        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertEquals(person.getAddress(), actual.getAddress(), () -> "The address is different!");
    }

    @DisplayName("When Create a Person with Success Should Contains Valid Gender In Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsValidGenderInReturnedPersonObject() {
        // Given / Arrange
        Set<String> validGenders = Set.of("Male", "M", "Female", "F", "Other");

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertTrue(validGenders.contains(actual.getGender()), () -> "The gender is different from 'Male', 'M', 'Female', 'F' or 'Other'!");
    }

    @DisplayName("When Create a Person with Success Should Contains Id Not Null In Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsIdNotNullInReturnedPersonObject() {
        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertNotNull(actual.getId(), () -> "The id should not have null!");
    }

    @DisplayName("When Create a Person with Null Email Should Throw Exception")
    @Test
    void testCreatePerson_WhithNullEmail_ShouldThrowIllegalArgumentException() {
    	// Given / Arrange
        person.setEmail("");
        var expectedException = "The Person email is null or empty";

        // When / Act & Then / Assert
        IllegalArgumentException actual = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> service.createPerson(person),
                () -> "Empty email should have cause an IllegalArgumentException");
        Assertions.assertEquals(
                actual.getMessage(),
                expectedException,
                () -> "Unexpected exception message!");
    }
}