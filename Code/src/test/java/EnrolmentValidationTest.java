import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seng2050.Course;
import seng2050.CourseService;
import seng2050.StudentService;

public class EnrolmentValidationTest {
    private final CourseService courseService = new CourseService();
    private final StudentService studentService = new StudentService();

    @Test
    public void testPrerequisite() {
        // Test enrolling in a course after meeting prerequisites
        // c1111 has completed the prerequisite course
        List<String> prerequisite = courseService.getPrerequisiteByCourseID("SENG2050");
        assertEquals(false, prerequisite.isEmpty());
        List<String> completedCourses = studentService.getCompletedCourseIDs("c1111", 102);
        boolean canEnrollWithPrerequisites = completedCourses.containsAll(prerequisite);
        assertEquals(true, canEnrollWithPrerequisites);

        // Test enrolling in a course after meeting some prerequisites
        // c2222 has completed one of the prerequisite courses
        List<String> completedCourses2 = studentService.getCompletedCourseIDs("c2222", 102);
        boolean canEnrollWithPrerequisites2 = completedCourses2.containsAll(prerequisite);
        assertEquals(false, canEnrollWithPrerequisites2);


        // Test enrolling in a course without meeting prerequisites
        // c3333 has not completed the prerequisite course
        List<String> completedCourses3 = studentService.getCompletedCourseIDs("c3333", 102);
        boolean canEnrollWithoutPrerequisites = completedCourses3.containsAll(prerequisite);
        assertEquals(false, canEnrollWithoutPrerequisites);

        // Test enrolling in a course with no prerequisites
        List<String> prerequisite2 = courseService.getPrerequisiteByCourseID("SENG1110");
        assertEquals(true, prerequisite2.isEmpty());
        boolean canEnrollWithNoPrerequisites = completedCourses2.containsAll(prerequisite2);
        assertEquals(true, canEnrollWithNoPrerequisites);
    }

        @Test
        public void testAssumedKnowledge() {
            // Test enrolling in a course after meeting all assumed knowledge
            // c1111 has completed the assumed knowledge course
            List<String> assumedKnowledge = courseService.getAssumedKnowledgeByCourseID("SENG2050");
            assertEquals(false, assumedKnowledge.isEmpty());
            List<String> completedCourses = studentService.getCompletedCourseIDs("c1111", 102);
            boolean canEnrollWithAssumedKnowledge = completedCourses.containsAll(assumedKnowledge);
            assertEquals(true, canEnrollWithAssumedKnowledge);

            // Test enrolling in a course after meeting some assumed knowledge
            // c2222 has completed one of the assumed knowledge courses
            List<String> completedCourses2 = studentService.getCompletedCourseIDs("c2222", 102);
            boolean canEnrollWithAssumedKnowledge2 = completedCourses2.containsAll(assumedKnowledge);
            assertEquals(false, canEnrollWithAssumedKnowledge2);

            // Test enrolling in a course without meeting assumed knowledge
            // c3333 has not completed the assumed knowledge course
            List<String> completedCourses3 = studentService.getCompletedCourseIDs("c3333", 102);
            boolean canEnrollWithoutAssumedKnowledge = completedCourses3.containsAll(assumedKnowledge);
            assertEquals(false, canEnrollWithoutAssumedKnowledge);

            // Test enrolling in a course with no assumed knowledge
            List<String> assumedKnowledge2 = courseService.getAssumedKnowledgeByCourseID("SENG1110");
            assertEquals(true, assumedKnowledge2.isEmpty());
            boolean canEnrollWithNoAssumedKnowledge = completedCourses2.containsAll(assumedKnowledge2);
            assertEquals(true, canEnrollWithNoAssumedKnowledge);
        }
    
    @Test 
    public void testMaxUnits() {
        Course testCourse1 = new Course("COMP0001", "Test Course1", 29);
        Course testCourse2 = new Course("COMP0002", "Test Course2", 1);
        Course testCourse3 = new Course("COMP0003", "Test Course3", 1);

        // SENG2050 has 10 credits
        List<Course> currentEnrolments = new java.util.ArrayList<>();
        
        // Test enrolling in a course that does not exceed the maximum unit limit (39)
        currentEnrolments.add(testCourse1);
        boolean ExceedingUnits1 = studentService.exceedMaxUnits("SENG2050", currentEnrolments);
        assertEquals(false, ExceedingUnits1);

        // Test enrolling in a course that exceeds the maximum unit limit (40)
        currentEnrolments.add(testCourse2);
        boolean ExceedingUnits2 = studentService.exceedMaxUnits("SENG2050", currentEnrolments);
        assertEquals(false, ExceedingUnits2);

        // Test enrolling in a course that reaches the maximum unit limit (41)
        currentEnrolments.add(testCourse3);
        boolean ExceedingUnits3 = studentService.exceedMaxUnits("SENG2050", currentEnrolments);
        assertEquals(true, ExceedingUnits3);

    }

    @Test 
    public void testCourseOpen() {
        // Test enrolling in a course that is open for enrolment
        boolean isCourseOpen1 = courseService.courseOpen("SENG2050", 102);
        assertEquals(true, isCourseOpen1);

        // Test enrolling in a course that is not open for enrolment
        boolean isCourseOpen2 = courseService.courseOpen("SENG4500", 101);
        assertEquals(false, isCourseOpen2);
    }

    @Test
    public void testReachMaxCapacity() {
        // Test enrolling in a course that has not reached maximum capacity
        boolean reachMaxCapacity1 = courseService.reachMaxCapacity("SENG2050", 102);
        assertEquals(false, reachMaxCapacity1);

        // Test enrolling in a course that has reached maximum capacity 
        // SENG2050 in semester 101 max capacity is 0
        boolean reachMaxCapacity2 = courseService.reachMaxCapacity("SENG2050", 101);
        assertEquals(true, reachMaxCapacity2);
    }

    @Test 
    public void testAlreadyEnroled() {
        // Test enrolling in a course that the student is already enrolled in
        boolean alreadyEnroled1 = studentService.alreadyEnroled("c1111", "SENG2050", 102);
        assertEquals(true, alreadyEnroled1);    

        // Test enrolling in a course that the student is not already enrolled in
        boolean alreadyEnroled2 = studentService.alreadyEnroled("c1111", "SENG1110", 102);
        assertEquals(false, alreadyEnroled2);
    }


}
