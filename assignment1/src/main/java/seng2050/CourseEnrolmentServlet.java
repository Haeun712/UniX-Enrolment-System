package seng2050;

import java.io.IOException;

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
        String assumedKnowledge = courseService.getAssumedKnowledgeByCourseID(courseID);
        String prerequisite = courseService.getPrerequisiteByCourseID(courseID);
        
        request.setAttribute("assumedknowledge", assumedKnowledge);
        request.setAttribute("prerequisite", prerequisite);
        request.setAttribute("openSidebar", true);
        request.setAttribute("targetCourse", targetCourse);
        request.getRequestDispatcher("findcourse.jsp").forward(request, response);

    }

    //In progress
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String course = request.getParameter("course");
        HttpSession session = request.getSession(false);
        session.setAttribute("course", course);
        request.getRequestDispatcher("success.jsp").forward(request, response);
    }
}
