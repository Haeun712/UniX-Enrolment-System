package seng2050;

import java.util.List;

public interface CourseDAO {
    Course getCourseByCourseID(String courseID);
    List<Course> getAllCourses();
    List<String> getAssumedKnowledgeByCourseID(String courseID);
    List<String> getPrerequisiteByCourseID(String courseID);
    int getMaxCapacityByCourseID(String courseID, int semesterID);
    boolean isCourseOpen(String courseID, int semesterID);
}
