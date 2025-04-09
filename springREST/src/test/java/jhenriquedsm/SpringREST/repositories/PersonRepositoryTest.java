package jhenriquedsm.SpringREST.repositories;

import jhenriquedsm.SpringREST.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @DisplayName("Given Person Object when Save then Return Saved Person")
    @Test
    void testGivenPersonObject_whenSave_thenReturnSavedPerson() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");

        // When / Act
        Person savedPerson = repository.save(person);

        // Then / Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertTrue(savedPerson.getId() > 0);
    }

    @DisplayName("Given Person List when Find All then Return Person List")
    @Test
    void testGivenPersonList_whenFindAll_thenReturnPersonList() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
        Person person1 = new Person("Théo", "Henrique", "Aracaju - SE", "Male", "thenrique@email.com");

        repository.save(person);
        repository.save(person1);
        // When / Act
        List<Person> personList = repository.findAll();

        // Then / Assert
        Assertions.assertNotNull(personList);
        Assertions.assertEquals(2, personList.size());
    }

    @DisplayName("Given Person Object when Find By Id then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindById_thenReturnPersonObject() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
        repository.save(person);

        // When / Act
        Person savedPerson = repository.findById(person.getId()).get();

        // Then / Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals(person.getId(), savedPerson.getId());
    }

    @DisplayName("Given Person Object when Find By Email then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
        repository.save(person);

        // When / Act
        Person savedPerson = repository.findByEmail(person.getEmail()).get();

        // Then / Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals(person.getEmail(), savedPerson.getEmail());
    }

    @DisplayName("Given Person Object when UpdatePerson then Return Updated Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
        repository.save(person);

        // When / Act
        Person savedPerson = repository.findById(person.getId()).get();
        savedPerson.setFirstName("Théo");
        savedPerson.setEmail("thenrique@email.com");

        Person updatedPerson = repository.save(savedPerson);

        // Then / Assert
        Assertions.assertNotNull(updatedPerson);
        Assertions.assertEquals("Théo", updatedPerson.getFirstName());
        Assertions.assertEquals("thenrique@email.com", updatedPerson.getEmail());
    }

    @DisplayName("Given Person Object when Delete then Remove Person")
    @Test
    void testGivenPersonObject_whenDelete_thenRemovePerson() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
        repository.save(person);

        // When / Act
        repository.deleteById(person.getId());

        Optional<Person> personOptional = repository.findById(person.getId());

        // Then / Assert
        Assertions.assertTrue(personOptional.isEmpty());
    }

    @DisplayName("Given First Name And Last Name when Find JPQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJPQL_thenReturnPersonObject() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
        repository.save(person);

        String firstName = "José";
        String lastName = "Henrique";

        // When / Act
        Person savedPerson = repository.findByJPQL(firstName, lastName);

        // Then / Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals(firstName, savedPerson.getFirstName());
        Assertions.assertEquals(lastName, savedPerson.getLastName());
    }

    @DisplayName("Given First Name And Last Name when FindJPQLNamedParameters then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJPQLNamedParameters_thenReturnPersonObject() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
        repository.save(person);

        String firstName = "José";
        String lastName = "Henrique";

        // When / Act
        Person savedPerson = repository.findByJPQLNamedParameters(firstName, lastName);

        // Then / Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals(firstName, savedPerson.getFirstName());
        Assertions.assertEquals(lastName, savedPerson.getLastName());
    }

    @DisplayName("Given First Name And Last Name when FindNativeSQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindNativeSQL_thenReturnPersonObject() {
        // Given / Arrange
        Person person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
        repository.save(person);

        String firstName = "José";
        String lastName = "Henrique";

        // When / Act
        Person savedPerson = repository.findByNativeSQL(firstName, lastName);

        // Then / Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals(firstName, savedPerson.getFirstName());
        Assertions.assertEquals(lastName, savedPerson.getLastName());
    }
}