package jhenriquedsm.SpringREST.repositories;

import jhenriquedsm.SpringREST.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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
}