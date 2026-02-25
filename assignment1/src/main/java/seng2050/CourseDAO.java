package seng2050;

import java.util.List;

public interface CourseDAO {
    Course getCourseByCourseID(String courseID);
    List<Course> getAllCourses();
}
