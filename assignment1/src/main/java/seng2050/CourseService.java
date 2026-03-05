package seng2050;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private CourseDAO crsDAO = new CourseDAOImpl();

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

    public Course getCourseByCourseID(String courseID) {
        return crsDAO.getCourseByCourseID(courseID);
    }

    public List<String> getAssumedKnowledgeByCourseID(String courseID) {
        List<String> assumedKnowCourses = crsDAO.getAssumedKnowledgeByCourseID(courseID);
        return assumedKnowCourses;
    }

    public List<String> getPrerequisiteByCourseID(String courseID) {
        List<String> PrerequisiteCourses = crsDAO.getPrerequisiteByCourseID(courseID);
        return PrerequisiteCourses;
    }
}
