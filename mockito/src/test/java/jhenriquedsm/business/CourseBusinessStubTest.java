package jhenriquedsm.business;

import jhenriquedsm.service.CourseService;
import jhenriquedsm.service.stubs.CourseServiceStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CourseBusinessStubTest {

    @Test
    void testCoursesRelatedToSpring_When_UsingAStub() {
    	// Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness courseBusiness = new CourseBusiness(stubService);

    	// When / Act
        var filteredCourses = courseBusiness.retrieveCoursesRelatedToSpring("José Henrique");

    	// Then / Assert
        Assertions.assertEquals(4, filteredCourses.size());
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAFooBarStudent() {
        // Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness courseBusiness = new CourseBusiness(stubService);

        // When / Act
        var filteredCourses = courseBusiness.retrieveCoursesRelatedToSpring("Foo Bar");

        // Then / Assert
        Assertions.assertEquals(0, filteredCourses.size());
    }
}