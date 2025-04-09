package jhenriquedsm.SpringREST.repositories;

import jhenriquedsm.SpringREST.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}