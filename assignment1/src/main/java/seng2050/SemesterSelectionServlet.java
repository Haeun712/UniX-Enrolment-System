package seng2050;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SemesterSelectionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Semester semester1 = new Semester();
        semester1.setIsOpen(true);
        semester1.setName("Semester 1 - 2026");

        Semester semester2 = new Semester();
        semester2.setIsOpen(false);
        semester2.setName("Midyear Session - 2026");

        List<Semester> semesters = new ArrayList<>();
        semesters.add(semester1);
        semesters.add(semester2);

        List<Map<String, Object>> results = new ArrayList<>();
        for (Semester s : semesters) {
            // Create a new Map to store the properties of this course to pass to the JSP
            Map<String, Object> map = new HashMap<>();
            map.put("name", s.getName());
            map.put("open", s.isOpen());
            results.add(map);
        }

        request.setAttribute("semesters", results);
        request.getRequestDispatcher("semesterSelection.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String semester = request.getParameter("semester");

        // Hardcoded authentication check
        if ("Semester 1 - 2026".equals(semester)) {
            HttpSession session = request.getSession();
            session.setAttribute("semester", semester);
            response.sendRedirect("FindCourseServlet");  // Redirect to FindCourseServlet
        } else {
            request.setAttribute("Error", true);
            request.setAttribute("errorTitle", "Enrolment Closed");
            request.setAttribute("errorContent", "You can no longer enrol in courses for the selected term. Please contact Student Services for assistance.");

            request.getRequestDispatcher("semesterSelection.jsp").forward(request, response);
        }
    }
}
