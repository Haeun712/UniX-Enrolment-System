package seng2050;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FindCourseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        Semester semester = (Semester) session.getAttribute("semester");

        //find student's current Enroled courses in selected semester by student No and Semester ID
        StudentService studentService = new StudentService();
        List<Course> currentEnrolments = studentService.getCurrentEnrolment(student.getStdNo(), semester.getSemesterID());

        request.setAttribute("currentEnrolments", currentEnrolments);
        request.getRequestDispatcher("findcourse.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        Semester semester = (Semester) session.getAttribute("semester");

        //find student's current Enroled courses in selected semester by student No and Semester ID
        StudentService studentService = new StudentService();
        List<Course> currentEnrolments = studentService.getCurrentEnrolment(student.getStdNo(), semester.getSemesterID());

        request.setAttribute("currentEnrolments", currentEnrolments);        

        String courseCode = request.getParameter("coursecode");
        CourseService courseService = new CourseService();

        //Search courses matching the input coursecode
        List<Course> results = courseService.searchCourse(courseCode);

        //store input and searchresults in session to maintain search results 
        session.setAttribute("input", courseCode);
        session.setAttribute("searchresults", results);

        request.getRequestDispatcher("findcourse.jsp").forward(request, response);
    }
}
