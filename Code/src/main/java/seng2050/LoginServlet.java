package seng2050;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        //Retrieve the 'name' parameter from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // authentication user
        StudentService stdService = new StudentService();
        Student student = stdService.authenticateStudent(username, password);
        if (student != null) {
            // Store student in session
            HttpSession session = request.getSession();
            session.setAttribute("student", student);
            session.setAttribute("username", username);
            response.sendRedirect("SemesterSelectionServlet");  // Redirect to SemesterSelectionServlet
        } else {
            request.setAttribute("error", true);
            request.setAttribute("errorTitle", "Login Failed");
            request.setAttribute("errorContent", "Please check your credentials and try again.");

            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
