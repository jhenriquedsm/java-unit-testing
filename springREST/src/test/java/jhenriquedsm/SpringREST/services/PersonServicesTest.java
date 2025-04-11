package jhenriquedsm.SpringREST.services;

import jhenriquedsm.SpringREST.exceptions.ResourceNotFoundException;
import jhenriquedsm.SpringREST.model.Person;
import jhenriquedsm.SpringREST.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServices services;

    private Person person;

    @BeforeEach
    public void setup() {
        // Given / Arrange
        person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
    }

    @DisplayName("Given Person Object When Save Person then Return Person Object")
    @Test
    void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject() {
    	// Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person)).willReturn(person);

    	// When / Act
        Person savedPerson = services.create(person);

    	// Then / Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals("José", savedPerson.getFirstName());
    }

    @DisplayName("Given Existing Email When Save Person then Throws Exception")
    @Test
    void testGivenExistingEmail_WhenSavePerson_thenThrowsException() {
        // Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(Optional.of(person));

        // When / Act
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            services.create(person);
        });

        // Then / Assert
        Mockito.verify(repository, never()).save(any(Person.class));
    }

    @DisplayName("Given People List When findAll People then Return People List")
    @Test
    void testGivenPeopleList_WhenFindAllPeople_thenReturnPeopleList() {
        // Given / Arrange
        Person person1 = new Person("Théo", "Henrique", "Aracaju - SE", "Male", "thenrique@email.com");
        given(repository.findAll()).willReturn(List.of(person, person1));

        // When / Act
        List<Person> peopleList = services.findAll();

        // Then / Assert
        Assertions.assertNotNull(peopleList);
        Assertions.assertEquals(2, peopleList.size());
    }

    @DisplayName("Given Empty People List When findAll People then Return Empty People List")
    @Test
    void testGivenEmptyPeopleList_WhenFindAllPeople_thenReturnEmptyPeopleList() {
        // Given / Arrange
        given(repository.findAll()).willReturn(Collections.emptyList());

        // When / Act
        List<Person> peopleList = services.findAll();

        // Then / Assert
        Assertions.assertTrue(peopleList.isEmpty());
        Assertions.assertEquals(0, peopleList.size());
    }
}