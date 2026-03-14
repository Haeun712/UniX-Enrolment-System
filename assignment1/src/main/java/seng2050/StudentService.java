package seng2050;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAOImpl();
    private StudentCourseRegistrationDAO regDAO = new StudentCourseRegistrationDAOImpl();
    private CourseDAO crsDAO = new CourseDAOImpl();

    /* Business logic to authenticate a student
     - Returns null if the student is not authenticated
     - Otherwise return the student Obj
     */
    public Student authenticateStudent(String stdNo, String password) {
        // Finds the student based on student Id
        Student student = studentDAO.getStudentByStdNo(stdNo);

        if (student != null) {
            PasswordSecurity pSec = new PasswordSecurity();
            if (pSec.verifyPassword(password, student)) {
                return student;
            }

        }
        return null;
    }

    /* Business logic to get student's current Enrolment (selected semester)
     - Returns list of courses that the student enrol this semeseter
     - Otherwise, null if student is not enrolled in any courses this semester
     */
    public List<Course> getCurrentEnrolment(String stdNo, int semesterID) {
        List<String> courseIDs = regDAO.getCurrentEnroledCourseIDs(stdNo, semesterID);
        //get list of Course obj based on list of courseIDs that student enroled
        List<Course> courses = new ArrayList<>();
        for (String courseID : courseIDs) {
            Course course = crsDAO.getCourseByCourseID(courseID);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }

    //get Courses that the student completed (regardless semester)
    public List<String> getCompletedCourseIDs(String stdNo, int semesterID) {
        List<String> allCourseIDs = regDAO.getAllEnroledCourseIDs(stdNo);
        List<String> currentCourseIDs = regDAO.getCurrentEnroledCourseIDs(stdNo, semesterID);
        List<String> completedCourseIDs = new ArrayList<>(allCourseIDs);
        completedCourseIDs.removeAll(currentCourseIDs);

        return completedCourseIDs;
    }

    // Check if the student already enroled in the course for this semester
    public boolean alreadyEnroled(String stdNo, String courseID, int semesterID) {
        List<String> currentCourseIDs = regDAO.getCurrentEnroledCourseIDs(stdNo, semesterID);
        return currentCourseIDs.contains(courseID);
    }

    //check if the student exceed the maximum unit limit (40 units) with this enrolment
    public boolean exceedMaxUnits(String courseID, List<Course> currentEnrolments) {

        Course targetCourse = crsDAO.getCourseByCourseID(courseID);
        
        int totalCredits = targetCourse.getCredits();
        for (Course course : currentEnrolments) {
            totalCredits += course.getCredits();
        }

        return totalCredits > 40;
    }


    //enrol the student in the course (add record in student_course_registration table)
    public Course enrolCourse(String stdNo, String courseID, int semesterID) {
        Course course = new Course();
        String enrolledCourseID = regDAO.enrolCourse(stdNo, courseID, semesterID);
        if (enrolledCourseID != null) {
            course = crsDAO.getCourseByCourseID(enrolledCourseID);
        }

        return course; // returns the enrolled course (if successful), otherwise null (enrollment failed)
    }

    
}
