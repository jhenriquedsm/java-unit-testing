package jhenriquedsm.SpringREST.integrationtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import jhenriquedsm.SpringREST.config.TestConfigs;
import jhenriquedsm.SpringREST.integrationtests.testcontainers.AbstractIntegrationTest;
import jhenriquedsm.SpringREST.model.Person;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static Person person;

    @BeforeAll
    public static void setup() {
        // Given / Arrange
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person("José", "Henrique", "Brasília - DF", "Male", "jhenrique@email.com");
    }

    @DisplayName("Given Person Object When Create One Person Should Return A Person Object")
    @Order(1)
    @Test
    void integrationTest_when_CreateOnePerson_ShouldReturnAPersonObject() throws JsonProcessingException {
    	var content = given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(person)
                .when()
                    .post()
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person createdPerson = objectMapper.readValue(content, Person.class);
        person = createdPerson;

        Assertions.assertNotNull(createdPerson);
        Assertions.assertNotNull(createdPerson.getId());
        Assertions.assertNotNull(createdPerson.getFirstName());
        Assertions.assertNotNull(createdPerson.getLastName());
        Assertions.assertNotNull(createdPerson.getAddress());
        Assertions.assertNotNull(createdPerson.getGender());
        Assertions.assertNotNull(createdPerson.getEmail());

        Assertions.assertTrue(createdPerson.getId() > 0);
        Assertions.assertEquals("José", createdPerson.getFirstName());
        Assertions.assertEquals("Henrique", createdPerson.getLastName());
        Assertions.assertEquals("Brasília - DF", createdPerson.getAddress());
        Assertions.assertEquals("Male", createdPerson.getGender());
        Assertions.assertEquals("jhenrique@email.com", createdPerson.getEmail());
    }

    @DisplayName("Given Person Object When Update One Person Should Return A Updated Person Object")
    @Order(2)
    @Test
    void integrationTest_when_UpdateOnePerson_ShouldReturnAUpdatedPersonObject() throws JsonProcessingException {
        var content = given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(person)
                .when()
                    .put()
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person createdPerson = objectMapper.readValue(content, Person.class);
        person = createdPerson;
        person.setFirstName("Théo");
        person.setEmail("thenrique@email.com");

        Assertions.assertNotNull(createdPerson);
        Assertions.assertNotNull(createdPerson.getId());
        Assertions.assertNotNull(createdPerson.getFirstName());
        Assertions.assertNotNull(createdPerson.getLastName());
        Assertions.assertNotNull(createdPerson.getAddress());
        Assertions.assertNotNull(createdPerson.getGender());
        Assertions.assertNotNull(createdPerson.getEmail());

        Assertions.assertTrue(createdPerson.getId() > 0);
        Assertions.assertEquals("Théo", createdPerson.getFirstName());
        Assertions.assertEquals("Henrique", createdPerson.getLastName());
        Assertions.assertEquals("Brasília - DF", createdPerson.getAddress());
        Assertions.assertEquals("Male", createdPerson.getGender());
        Assertions.assertEquals("thenrique@email.com", createdPerson.getEmail());
    }

    @DisplayName("Given Person Object When findById Should Return A Person Object")
    @Order(3)
    @Test
    void integrationTest_when_findById_ShouldReturnAPersonObject() throws JsonProcessingException {
        var content = given().spec(specification)
                    .pathParam("id", person.getId())
                .when()
                    .get("{id}")
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person foundPerson = objectMapper.readValue(content, Person.class);

        Assertions.assertNotNull(foundPerson);
        Assertions.assertNotNull(foundPerson.getId());
        Assertions.assertNotNull(foundPerson.getFirstName());
        Assertions.assertNotNull(foundPerson.getLastName());
        Assertions.assertNotNull(foundPerson.getAddress());
        Assertions.assertNotNull(foundPerson.getGender());
        Assertions.assertNotNull(foundPerson.getEmail());

        Assertions.assertTrue(foundPerson.getId() > 0);
        Assertions.assertEquals("José", foundPerson.getFirstName());
        Assertions.assertEquals("Henrique", foundPerson.getLastName());
        Assertions.assertEquals("Brasília - DF", foundPerson.getAddress());
        Assertions.assertEquals("Male", foundPerson.getGender());
        Assertions.assertEquals("jhenrique@email.com", foundPerson.getEmail());
    }

    @DisplayName("Given Person Object When findAll Should Return A People List")
    @Order(4)
    @Test
    void integrationTest_when_findAll_ShouldReturnAPeopleList() throws JsonProcessingException {

        Person anotherPerson = new Person("Théo", "Henrique", "Brasília - DF", "Male", "thenrique@email.com");

        given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(anotherPerson)
                .when()
                    .post()
                .then()
                    .statusCode(200);

        var content = given().spec(specification)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person[] myArray = objectMapper.readValue(content, Person[].class);
        List<Person> peopleList = Arrays.asList(myArray);

        Person foundPersonOne = peopleList.get(0);

        Assertions.assertNotNull(foundPersonOne);
        Assertions.assertNotNull(foundPersonOne.getId());
        Assertions.assertNotNull(foundPersonOne.getFirstName());
        Assertions.assertNotNull(foundPersonOne.getLastName());
        Assertions.assertNotNull(foundPersonOne.getAddress());
        Assertions.assertNotNull(foundPersonOne.getGender());
        Assertions.assertNotNull(foundPersonOne.getEmail());

        Assertions.assertTrue(foundPersonOne.getId() > 0);
        Assertions.assertEquals("José", foundPersonOne.getFirstName());
        Assertions.assertEquals("Henrique", foundPersonOne.getLastName());
        Assertions.assertEquals("Brasília - DF", foundPersonOne.getAddress());
        Assertions.assertEquals("Male", foundPersonOne.getGender());
        Assertions.assertEquals("jhenrique@email.com", foundPersonOne.getEmail());

        Person foundPersonTwo = peopleList.get(1);

        Assertions.assertNotNull(foundPersonTwo);
        Assertions.assertNotNull(foundPersonTwo.getId());
        Assertions.assertNotNull(foundPersonTwo.getFirstName());
        Assertions.assertNotNull(foundPersonTwo.getLastName());
        Assertions.assertNotNull(foundPersonTwo.getAddress());
        Assertions.assertNotNull(foundPersonTwo.getGender());
        Assertions.assertNotNull(foundPersonTwo.getEmail());

        Assertions.assertTrue(foundPersonTwo.getId() > 0);
        Assertions.assertEquals("Théo", foundPersonTwo.getFirstName());
        Assertions.assertEquals("Henrique", foundPersonTwo.getLastName());
        Assertions.assertEquals("Brasília - DF", foundPersonTwo.getAddress());
        Assertions.assertEquals("Male", foundPersonTwo.getGender());
        Assertions.assertEquals("thenrique@email.com", foundPersonTwo.getEmail());
    }

    @DisplayName("Given Person Object When Delete Should Return No Content")
    @Order(5)
    @Test
    void integrationTest_when_Delete_ShouldReturnNoContent() throws JsonProcessingException {
        given().spec(specification)
                    .pathParam("id", person.getId())
                .when()
                    .delete("{id}")
                .then()
                    .statusCode(204);
    }
}