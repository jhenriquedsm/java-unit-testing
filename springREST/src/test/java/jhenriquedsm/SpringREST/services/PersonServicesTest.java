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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

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

    @DisplayName("Given Person Id When findById then Return Person Object")
    @Test
    void testGivenPersonId_WhenFindById_thenReturnPersonObject() {
        // Given / Arrange
        given(repository.findById(anyLong())).willReturn(Optional.of(person));

        // When / Act
        Person savedPerson = services.findById(1L);

        // Then / Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals("José", savedPerson.getFirstName());
    }

    @DisplayName("Given Person Object When Update Person then Return Update Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatePersonObject() {
        // Given / Arrange
        person.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person));

        person.setEmail("thenrique@email.com");
        person.setFirstName("Théo");
        given(repository.save(person)).willReturn(person);

        // When / Act
        Person updatedPerson = services.update(person);

        // Then / Assert
        Assertions.assertNotNull(updatedPerson);
        Assertions.assertEquals("Théo", updatedPerson.getFirstName());
        Assertions.assertEquals("thenrique@email.com", updatedPerson.getEmail());
    }

    @DisplayName("Given Person Id When Delete Person then Do Nothing")
    @Test
    void testGivenPersonId_WhenDeletePerson_thenDoNothing() {
        // Given / Arrange
        person.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person));
        willDoNothing().given(repository).delete(person);

        // When / Act
        services.delete(person.getId());

        // Then / Assert
        Mockito.verify(repository, times(1)).delete(person);
    }
}