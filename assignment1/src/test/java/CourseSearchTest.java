import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import seng2050.Course;
import seng2050.CourseService;

public class CourseSearchTest {
    private final CourseService courseService = new CourseService();

    @Test
    public void testSearchCourses() {
        // Test searching for a course that exists
        List<Course> searchResult1 = courseService.searchCourse("SENG2050");
        assertNotNull(searchResult1);  
        assertEquals("SENG2050", searchResult1.get(0).getCourseID());

        // Test searching for a course that does not exist
        List<Course> searchResult2 = courseService.searchCourse("NONEXISTENT");
        assertEquals(true, searchResult2.isEmpty());

        // Test searching with an empty string
        List<Course> searchResult3 = courseService.searchCourse("");
        assertEquals(true, searchResult3.isEmpty());

        // Test searching with a partial course ID
        List<Course> searchResult4 = courseService.searchCourse("SENG");
        assertEquals(false, searchResult4.isEmpty());
    }

}
