import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import seng2050.Course;
import seng2050.StudentService;

public class EnrolmentTest {
    private final StudentService studentService = new StudentService();

    @Test
    public void testEnrolment() {
        // Test enrolling in a valid course
        Course enroledCCourse = studentService.enrolCourse("c1111", "INFT3060", 102);
        assertNotNull(enroledCCourse);
    }

    @Test
    public void testDropCourse() {
        // Test dropping a course
        Course droppedCourse = studentService.dropCourse("c1111", "INFT3060", 102);
        assertNotNull(droppedCourse);
    }
}
