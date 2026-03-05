package seng2050;

import java.util.List;

public interface  StudentCourseRegistrationDAO {
    public List<String> getCurrentEnroledCourseIDs(String stdNo, int semesterID);
    public List<String> getAllEnroledCourseIDs(String stdNo);
}
