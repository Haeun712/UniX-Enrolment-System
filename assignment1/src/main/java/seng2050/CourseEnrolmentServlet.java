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

        String course = request.getParameter("coursecode");
        request.setAttribute("targetcourse", course);
        request.setAttribute("openSidebar", true);
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
