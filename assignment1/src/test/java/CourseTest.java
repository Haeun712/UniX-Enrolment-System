import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import seng2050.Course;

public class CourseTest {
    
    private final Course course = new Course("SENG1234", "Test Course", 3);

    @Test
    public void testGetId() {
        assertEquals("SENG1234", course.getCourseID());
    }

    @Test
    public void testGetCourseName() {
        assertEquals("Test Course", course.getCourseName());
    }

    @Test
    public void testGetCredits() {
        assertEquals(3, course.getCredits());
    }

    @Test
    public void testEquals() {
        Course sameCourse = new Course("SENG1234", "Another Course Name", 4);
        Course differentCourse = new Course("SENG5678", "Test Course", 3);

        assertEquals(course, sameCourse); // Should be equal based on courseID

        assertNotEquals(course, differentCourse); // Should not be equal based on courseID
    }

}