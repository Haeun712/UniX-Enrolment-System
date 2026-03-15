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
        //Store current Enroled courses in session
        session.setAttribute("currentEnrolments", currentEnrolments);

        String searchString = request.getParameter("search");
        CourseService courseService = new CourseService();

        // Check if user searches 
        if (searchString != null) {
            //Search courses matching the input coursecode
            List<Course> results = courseService.searchCourse(searchString);

            //store searchresults in session to maintain search results 
            session.setAttribute("searchresults", results);
            // Set a flag to indicate that search results should be displayed
            session.setAttribute("showSearchResult", true);
            request.setAttribute("search", searchString);
        }

        request.getRequestDispatcher("findcourse.jsp").forward(request, response);

    }
}
