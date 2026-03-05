package seng2050;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CourseEnrolmentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get course Obj for selected course
        CourseService courseService = new CourseService();
        String courseID = request.getParameter("coursecode");
        Course targetCourse = courseService.getCourseByCourseID(courseID);
        // Convert assumed knowledge course list into a single comma-separated string
        List<String> assumedKnowCourses = courseService.getAssumedKnowledgeByCourseID(courseID);
        String assumedKnowledge;
        if (assumedKnowCourses == null || assumedKnowCourses.isEmpty()) {
            assumedKnowledge = "No assumed knowledge listed";
        } else {
            assumedKnowledge = String.join(", ", assumedKnowCourses);
        }

        // Convert prerequisite course list into a single comma-separated string
        List<String> PrerequisiteCourses = courseService.getPrerequisiteByCourseID(courseID);
        String prerequisite;
        if (PrerequisiteCourses == null || PrerequisiteCourses.isEmpty()) {
            prerequisite = "No prerequisite listed";
        } else {
            prerequisite = String.join(", ", PrerequisiteCourses);
        }

        request.setAttribute("assumedknowledge", assumedKnowledge);
        request.setAttribute("prerequisite", prerequisite);
        request.setAttribute("openSidebar", true);
        request.setAttribute("targetCourse", targetCourse);
        request.getRequestDispatcher("findcourse.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseID = request.getParameter("courseID");
        String action = request.getParameter("action");


        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        Semester semester = (Semester) session.getAttribute("semester");
        if("enrol".equals(action)){
            //add enrolment
            session.setAttribute("course", courseID);
            request.getRequestDispatcher("success.jsp").forward(request, response);

        }

        

        StudentService studentService = new StudentService();
        List<String> completedCourseIDs = studentService.getCompletedCourseIDs(student.getStdNo(), semester.getSemesterID());

        CourseService courseService = new CourseService();

        //Check if the student completed the pre-reequisite
        List<String> PrerequisiteCourses = courseService.getPrerequisiteByCourseID(courseID);

        if (!completedCourseIDs.containsAll(PrerequisiteCourses)) {
            request.setAttribute("error", true);
            request.setAttribute("errorTitle", "Prerequisite Not Met");
            request.setAttribute("errorContent", "You cannot enrol without completing pre-requisite courses");

            request.getRequestDispatcher("FindCourseServlet").forward(request, response);
        }
        //Check if the student completed the assumed knowledge
        List<String> assumedKnowCourses = courseService.getAssumedKnowledgeByCourseID(courseID);

        if (!completedCourseIDs.containsAll(assumedKnowCourses)) {
            request.setAttribute("warning", true);
            request.setAttribute("warningTitle", "Assumed Knowledge Warning");
            request.setAttribute("warningContent", "You have not completed the assumed knowledge for this course. Do you want to proceed with enrolment?");

            request.getRequestDispatcher("FindCourseServlet").forward(request, response);
        }

        //if the student completed the assumed knowledge and pre-requistie
        request.setAttribute("confirm", true);
        request.setAttribute("confirmTitle", courseID+ ": Confirm Enrolment");
        request.setAttribute("confirmContent", "Are you sure you want to enrol in this course?");
        request.setAttribute("course",courseID);
        request.getRequestDispatcher("FindCourseServlet").forward(request, response);

        //add enrolment

        
    }
}
