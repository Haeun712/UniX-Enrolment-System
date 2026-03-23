package seng2050;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private CourseDAO crsDAO = new CourseDAOImpl();
    private StudentCourseRegistrationDAO regDAO = new StudentCourseRegistrationDAOImpl();

    /* Business logic to search for courses by course ID (Course Code)
        - Performs a case-insensitive search
        - Returns a list of Course objects whose courseID contains the input string
     */
    public List<Course> searchCourse(String input) {
        List<Course> allCourses = crsDAO.getAllCourses();
        List<Course> matchCourses = new ArrayList<>();

        // input is not empty
        if (input != null && !input.trim().isEmpty()) {
            String trimmedInput = input.trim();

            for (Course course : allCourses) {
                String courseID = course.getCourseID();
                if (courseID.toLowerCase().contains(trimmedInput.toLowerCase())) {
                    matchCourses.add(course);
                }
            }
        }

        return matchCourses;
    }

    // Get course details by course ID
     public Course getCourseDetails(String courseID) {
        return crsDAO.getCourseByCourseID(courseID);
     }

     // Get all courses
     public List<Course> getAllCourses() {
        return crsDAO.getAllCourses();
     }

    // Get Course object by course ID
    public Course getCourseByCourseID(String courseID) {
        return crsDAO.getCourseByCourseID(courseID);
    }

    // Get assumed knowledge courses by course ID
    public List<String> getAssumedKnowledgeByCourseID(String courseID) {
        List<String> assumedKnowCourses = crsDAO.getAssumedKnowledgeByCourseID(courseID);
        return assumedKnowCourses;
    }

    // Get prerequisite courses by course ID
    public List<String> getPrerequisiteByCourseID(String courseID) {
        List<String> PrerequisiteCourses = crsDAO.getPrerequisiteByCourseID(courseID);
        return PrerequisiteCourses;
    }

    // if the course reach the maximum capacity for this semester, return true. Otherwise, false
     public boolean reachMaxCapacity(String courseID, int semesterID) {
        int max = crsDAO.getMaxCapacityByCourseID(courseID, semesterID);
        int studentCount = regDAO.getEnroledStudentCount(courseID, semesterID);
        return studentCount >= max;
    }

    // Check if the course is open for enrolment in this semester, 
    // return true if the course is offered in this semester. Otherwise, false
    public boolean courseOpen(String courseID, int semesterID) {
        boolean isCourseOpen = crsDAO.isCourseOpen(courseID, semesterID);
        return isCourseOpen;
    }
}
