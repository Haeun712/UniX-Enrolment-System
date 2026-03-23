package seng2050;

import java.util.List;

public interface SemesterDAO {
    Semester getSemesterBySemesterID(int semesterID); 
    List<Semester> getAllSemesters();
}
