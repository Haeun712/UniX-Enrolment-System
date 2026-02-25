<%@ page import="java.util.*" %>
<%@ page import="seng2050.Semester" %>
<%@ page import="seng2050.Student" %>
<%@ page import="seng2050.Course" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>UniX - Enrolment</title>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        th,
        td {
            border: 1px solid #ccc;
            padding: 5px 8px;
            text-align: left;
        }
        .resultbox {
            width: 100%;
            padding: 10px;
            background-color: white;
            color: black;
            border: #c5c5c5 1px solid;
            border-radius: 3px;
            margin: 10px 0;
            text-align: left;
        }

        .resultbox:hover {
            background-color: #D9E7FF;
        }
    </style>
</head>

<body>
    <header>
        <img src="/images/UniXLogo.jpg" height="32px" />
        <h1>Course Enrolment</h1>
        <div class="icon-container" style="gap:20px">
            <div class="icon-container">
                <img src="/images/User.png" height="24px" />
                <% Student student=(Student) session.getAttribute("student"); %>
                    <p>
                        <%=student.getGivenNames() %> <%=student.getLastName() %>
                    </p>
            </div>
            <button class="icon-container" onclick="location.href='LogoutServlet'">
                <img src="/images/LogOut.png" height="24px" />
                Log Out</button>
        </div>
        </div>
    </header>


    <div class="container" style="margin-top: 22px;">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            <button class="icon-container" style="padding: 2px 2px;margin-bottom: 10px;border-radius: 100px; position: relative;
    left: -4px;" onclick="location.href='SemesterSelectionServlet'">
                <img src="/images/Arrow left.png" height="24px" /></button>
            <h2>Find Course</h2>
            <hr>
            <% Semester semester=(Semester) session.getAttribute("semester"); %>
            <p style="margin-bottom: 10px;">Search for courses offered in Semester <%=semester.getSemester() %> - <%=semester.getYear()%> by Course Code</p>
            <form action="FindCourseServlet" method="post">
                <label for="coursecode">Course Code</label><br>
                <input type="text" id="coursecode" name="coursecode" placeholder="Enter Course Code (e.g. SENG2050)"
                    value="<%=session.getAttribute("input") != null ? session.getAttribute("input") : "" %>" style="width: 50%; max-width: 250px">
                <button type="submit" style="padding: 5px 15px;font-size: 16px;margin-left: 20px;">Search</button>
            </form>
        </div>
    </div>

    <div class="container">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            <h2>Select a Course</h2>
            <hr>
            <p style="margin-bottom: 10px;">Select a course to enrol in Semester <%=semester.getSemester() %> - <%=semester.getYear()%></p>
            <%  List<Course> courses=(List<Course>) session.getAttribute("searchresults");
             if(courses!=null && !courses.isEmpty()) {
                 for (Course course : courses) { %>
                <form action="CourseEnrolmentServlet" method="get">
                    <input type="hidden" name="coursecode" value="<%=course.getCourseID() %>" />
                    <button class="resultbox" type="submit" onclick="toggleSidebar()">
                            <p style="margin-right: 20px;color:#003AA6">
                                <%= course.getCourseID() %>
                            </p>
                        <p style="font-weight: bold;">
                            <%= course.getCourseName() %>
                        </p>
                    </button>
                </form>
            <% } %>
            <%} else {%>
                <p style="margin-top: 40px; font-weight: bold;">No courses found</p>
                <% } %>
        </div>
    </div>

    <div class="container">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            <h2>Current Enrolments</h2>
            <% List<Course> currentEnrolments = (List<Course>) request.getAttribute("currentEnrolments");
                if(currentEnrolments!=null && !currentEnrolments.isEmpty()) { %>
            <table style="width: 100%; border: 1px solid #c5c5c5;border-collapse: collapse; margin-top: 20px;">
                <thead style="background-color: #c5c5c5;">
                    <tr>
                        <th>CourseID</th>
                        <th>Course Name</th>
                        <th>Credits</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Course course : currentEnrolments) { %>
                    <tr>
                        <td><%=course.getCourseID() %></td>
                        <td><%=course.getCourseName() %></td>
                        <td><%=course.getCredits() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
             <%} else {%>
                <p style="margin-top: 40px; font-weight: bold;">You are not enrolled in any courses this semester.</p>
             <% } %>
        </div>
    </div>
    <%@ include file="courseenrolment.jsp" %>

        <%@ include file="/modal/error.jsp" %>
</body>

</html>