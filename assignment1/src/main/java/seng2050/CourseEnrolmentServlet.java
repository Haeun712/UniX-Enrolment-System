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
        

        // Convert prerequisite course list into a single comma-separated string
        List<String> PrerequisiteCourses = courseService.getPrerequisiteByCourseID(courseID);

        HttpSession session = request.getSession();

        session.setAttribute("assumedknowledge", assumedKnowCourses);
        session.setAttribute("prerequisite", PrerequisiteCourses);
        session.setAttribute("targetCourse", targetCourse);

        request.setAttribute("showSidebar", true);
        request.setAttribute("openSidebar", true);
        request.getRequestDispatcher("findcourse.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseID = request.getParameter("courseID");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        Semester semester = (Semester) session.getAttribute("semester");
        @SuppressWarnings("unchecked")
        List<Course> currentEnrolments = (List<Course>) session.getAttribute("currentEnrolments");

        CourseService courseService = new CourseService();
        StudentService StudentService = new StudentService();
        
        if ("enrol".equals(action)) {
            //Check if the student already enroled in the course for this semester
            if (StudentService.alreadyEnroled(student.getStdNo(), courseID, semester.getSemesterID())) {
                request.setAttribute("error", true);
                request.setAttribute("errorTitle", "Already Enrolled");
                request.setAttribute("errorContent", "You have already enrolled in " + courseID + " for this semester.");
                request.setAttribute("showSidebar", true);

                request.getRequestDispatcher("findcourse.jsp").forward(request, response);
                return;
            }

            
            //Check if the student exceed the maximum unit limit with this enrolment
            if (StudentService.exceedMaxUnits(courseID, currentEnrolments)) {
                request.setAttribute("error", true);
                request.setAttribute("errorTitle", "Maximum Units Exceeded");
                request.setAttribute("errorContent", "You cannot enrol in this course because it would exceed the 40-unit limit for this semester.");
                request.setAttribute("showSidebar", true);

                request.getRequestDispatcher("findcourse.jsp").forward(request, response);
                return;
            }

            //Check if the course is open for enrolment in this semester (offer this course in this semester or not)
            if(!courseService.courseOpen(courseID, semester.getSemesterID())) {
                request.setAttribute("error", true);
                request.setAttribute("errorTitle", "Course Not Offered");
                request.setAttribute("errorContent", "You cannot enrol in this course because it is not offered in this semester.");
                request.setAttribute("showSidebar", true);

                request.getRequestDispatcher("findcourse.jsp").forward(request, response);
                return;
            }

            //Check if the course reach maximum enrolment capacity
            if (courseService.reachMaxCapacity(courseID, semester.getSemesterID())) {
                request.setAttribute("error", true);
                request.setAttribute("errorTitle", "Course Full");
                request.setAttribute("errorContent", "You cannot enrol in this course because it has reached its maximum capacity.");
                request.setAttribute("showSidebar", true);

                request.getRequestDispatcher("findcourse.jsp").forward(request, response);
                return;
            }
            
            //enrol the student in the course (add record in student_course_registration table)
            Course enrolledCourse = StudentService.enrolCourse(student.getStdNo(), courseID, semester.getSemesterID());

            //Update current enrolment in session
            currentEnrolments = StudentService.getCurrentEnrolment(student.getStdNo(), semester.getSemesterID());

            session.setAttribute("currentEnrolments", currentEnrolments);
            session.setAttribute("course", enrolledCourse);
            response.sendRedirect("success.jsp");
        } else if ("confirm".equals(action)) {
            List<String> completedCourseIDs = StudentService.getCompletedCourseIDs(student.getStdNo(), semester.getSemesterID());

            //Check if the student completed the pre-reequisite
            @SuppressWarnings("unchecked")
            List<String> PrerequisiteCourses = (List<String>) session.getAttribute("prerequisite");

            if (!completedCourseIDs.containsAll(PrerequisiteCourses)) {
                request.setAttribute("error", true);
                request.setAttribute("errorTitle", "Prerequisite Not Met");
                request.setAttribute("errorContent", "You cannot enrol without completing pre-requisite courses");

                request.setAttribute("showSidebar", true);
                request.getRequestDispatcher("findcourse.jsp").forward(request, response);
                return;
            }
            //Check if the student completed the assumed knowledge
            @SuppressWarnings("unchecked")
            List<String> assumedKnowCourses = (List<String>) session.getAttribute("assumedknowledge");

            if (!completedCourseIDs.containsAll(assumedKnowCourses)) {
                request.setAttribute("warning", true);
                request.setAttribute("warningTitle", "Assumed Knowledge Warning");
                request.setAttribute("warningContent", "You have not completed the assumed knowledge for this course. Do you want to proceed with enrolment?");

                request.setAttribute("showSidebar", true);
                request.getRequestDispatcher("findcourse.jsp").forward(request, response);
                return;
            }

            //if the student completed the assumed knowledge and pre-requistie
            request.setAttribute("confirm", true);
            request.setAttribute("confirmTitle", "Confirm Enrolment");
            request.setAttribute("confirmContent", "Are you sure you want to enrol in " + courseID + "?");
            request.setAttribute("course", courseID);
            request.setAttribute("showSidebar", true);
            request.getRequestDispatcher("findcourse.jsp").forward(request, response);
        }
    }
}
