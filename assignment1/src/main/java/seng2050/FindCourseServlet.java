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

public class FindCourseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("findcourse.jsp");  // Redirect to semesterSelection

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseCode = request.getParameter("coursecode");
        List<Map<String, Object>> results = new ArrayList<>();
        if (courseCode != null && !courseCode.isEmpty()) {
            List<Course> courses = new ArrayList<>();
            Course course1 = new Course();
            course1.setCode("SENG2050");
            course1.setName("Web Engineering ");
            course1.setIsOpen(true);
            course1.setLocation("Callaghan");
            course1.setUnit(10);

            Course course2 = new Course();
            course2.setCode("SENG2050A");
            course2.setName("Advanced Web Development");
            course2.setIsOpen(false);
            course2.setLocation("Callaghan");
            course2.setUnit(10);

            Course course3 = new Course();
            course3.setCode("SENG2050B");
            course3.setName("Web Application Architecture");
            course3.setIsOpen(false);
            course3.setLocation("Callaghan");
            course3.setUnit(10);

            courses.add(course1);
            courses.add(course2);
            courses.add(course3);

            

            for (Course c : courses) {
                // Create a new Map to store the properties of this course to pass to the JSP
                Map<String, Object> map = new HashMap<>();
                map.put("name", c.getName());
                map.put("code", c.getCode());
                map.put("open", c.isOpen());
                results.add(map);
            }

        }
        request.setAttribute("courses", results);
        request.getRequestDispatcher("findcourse.jsp").forward(request, response);
    }
}
