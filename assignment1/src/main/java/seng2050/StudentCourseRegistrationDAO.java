package seng2050;

import java.util.List;

public interface  StudentCourseRegistrationDAO {
    public List<String> getCurrentEnroledCourseIDs(String stdNo, int semesterID);
    public List<String> getAllEnroledCourseIDs(String stdNo);
    public int getEnroledStudentCount(String courseID, int semesterID);
    public String enrolCourse(String stdNo, String courseID, int semesterID);
}
