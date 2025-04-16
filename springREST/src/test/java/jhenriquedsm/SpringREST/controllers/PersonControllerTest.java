package jhenriquedsm.SpringREST.controllers;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jhenriquedsm.SpringREST.exceptions.ResourceNotFoundException;
import jhenriquedsm.SpringREST.model.Person;
import jhenriquedsm.SpringREST.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonServices service;

    private Person person;

    @BeforeEach
    public void setup() {
        // Given / Arrange
        person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
    }

    @DisplayName("Given Person Object When Create Person then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenCreatePerson_thenReturnSavedPerson() throws Exception {
    	// Given / Arrange
        given(service.create(person))
                .willAnswer((invocation) -> invocation.getArgument(0));

    	// When / Act
        ResultActions response = mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)));

    	// Then / Assert
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    @DisplayName("Given List of People When findAll People then Return People List")
    @Test
    void testGivenListOfPeople_WhenFindAllPeople_thenReturnPeopleList() throws Exception {
        // Given / Arrange
        List<Person> peopleList = new ArrayList<>();
        peopleList.add(person);
        peopleList.add(new Person("Théo", "Henrique", "Brasília - DF", "Male", "thenrique@email.com"));

        given(service.findAll())
                .willReturn(peopleList);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person"));

        // Then / Assert
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(peopleList.size())));
    }

    @DisplayName("Given Person Id When findById then Return Person Object")
    @Test
    void testGivenPersonId_WhenFindById_thenReturnPersonObject() throws Exception {
        // Given / Arrange
        Long personId = 1L;
        given(service.findById(personId))
                .willReturn(person);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then / Assert
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    @DisplayName("Given Invalid Person Id When findById then Return Not Found")
    @Test
    void testGivenInvalidPersonId_WhenFindById_thenReturnNotFound() throws Exception {
        // Given / Arrange
        Long personId = 1L;
        given(service.findById(personId))
                .willThrow(ResourceNotFoundException.class);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then / Assert
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @DisplayName("Given Updated Person When Update then Return Updated Person Object")
    @Test
    void testGivenUpdatedPerson_WhenUpdate_thenReturnUpdatePersonObject() throws Exception {
        // Given / Arrange
        Long personId = 1L;
        given(service.findById(personId)).willReturn(person);
        given(service.update(ArgumentMatchers.any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // When / Act
        Person updatedPerson = new Person("Théo", "Henrique", "Brasília - DF", "Male", "thenrique@email.com");
        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPerson)));

        // Then / Assert
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
    }

    @DisplayName("Given Unexistent Person When Update then Return NotFound")
    @Test
    void testGivenUnexistentPerson_WhenUpdate_thenReturnNotFound() throws Exception {
        // Given / Arrange
        Long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(service.update(ArgumentMatchers.any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(1));

        // When / Act
        Person updatedPerson = new Person("Théo", "Henrique", "Brasília - DF", "Male", "thenrique@email.com");
        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPerson)));

        // Then / Assert
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @DisplayName("Given Person Id When Delete then Return No Content")
    @Test
    void testGivenPersonId_WhenDelete_thenReturnNoContent() throws Exception {
        // Given / Arrange
        Long personId = 1L;
        willDoNothing().given(service).delete(personId);

        // When / Act
        ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

        // Then / Assert
        response.andExpect(status().isNoContent())
                .andDo(print());
    }
}