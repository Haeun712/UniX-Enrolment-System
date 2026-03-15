package seng2050;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SemesterSelectionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SemesterService smsService = new SemesterService();
        List<Semester> semesters = smsService.getAllSemesters();

        request.setAttribute("semesters", semesters);
        request.getRequestDispatcher("semesterSelection.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String semesterIDStr = request.getParameter("semesterID");
        int semesterID = Integer.parseInt(semesterIDStr);

        SemesterService smsService = new SemesterService();
        //get selected semster obj by semesterID from DB
        Semester semester = smsService.getSemesterBySemesterID(semesterID);

        // if semester open
        if (semester.getOpenForEnrolment()) {
            HttpSession session = request.getSession();
            
            //store selected semester in session
            session.setAttribute("semester", semester);
            if ((Boolean) session.getAttribute("showSearchResult")  != null)
            {
                session.removeAttribute("showSearchResult");
            }
            response.sendRedirect("FindCourseServlet");  // Redirect to FindCourseServlet
        } else {
            request.setAttribute("error", true);
            request.setAttribute("errorTitle", "Enrolment Closed");
            request.setAttribute("errorContent", "You can no longer enrol in courses for the selected term. Please contact Student Services for assistance.");

            List<Semester> semesters = smsService.getAllSemesters();
            request.setAttribute("semesters", semesters);
            request.getRequestDispatcher("semesterSelection.jsp").forward(request, response);
        }
    }
}
