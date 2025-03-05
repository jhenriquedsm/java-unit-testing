package jhenriquedsm.business;

import jhenriquedsm.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CourseBusinessMockWithBDDTest {
    CourseService mockService;
    CourseBusiness courseBusiness;
    List<String> courses;

    @BeforeEach
        void setup() {
        // Given / Arrange
        mockService = mock(CourseService.class);
        courseBusiness = new CourseBusiness(mockService);
        courses = Arrays.asList("REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker");
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAMock() {
        // Given / Arrange
        given(mockService.retrieveCourses("José Henrique")).willReturn(courses);

    	// When / Act
        var filteredCourses = courseBusiness.retrieveCoursesRelatedToSpring("José Henrique");

    	// Then / Assert
        assertThat(filteredCourses.size(), is(4));
    }
}