package jhenriquedsm.business;

import jhenriquedsm.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class CourseBusinessMockTest {
    CourseService mockService;
    CourseBusiness courseBusiness;

    @BeforeEach
        void setup() {
        // Given / Arrange
        mockService = mock(CourseService.class);
        courseBusiness = new CourseBusiness(mockService);
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAMock() {
    	// When / Act
        var filteredCourses = courseBusiness.retrieveCoursesRelatedToSpring("José Henrique");

    	// Then / Assert
        Assertions.assertEquals(4, filteredCourses.size());
    }
}