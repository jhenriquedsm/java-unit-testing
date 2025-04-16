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
}