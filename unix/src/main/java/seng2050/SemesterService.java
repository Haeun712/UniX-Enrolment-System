package seng2050;

import java.util.List;

public class SemesterService {
    private SemesterDAO semesterDAO = new SemesterDAOImpl();

    /* Business logic to get all semesters
     - Returns all semsters stored in DB (semster table)
    */

    public List<Semester> getAllSemesters() {
        // Finds the student based on student Id
        List<Semester> semesters = semesterDAO.getAllSemesters();
        return semesters;
     }

     /* Business logic to get a semester by semesterID
     - Returns the semester Obj
    */

    public Semester getSemesterBySemesterID(int semesterID) {
        // Finds the semester based on semester Id
        return semesterDAO.getSemesterBySemesterID(semesterID);
     }
}
