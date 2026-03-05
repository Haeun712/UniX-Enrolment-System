<%@ page import="seng2050.Course" %>
<%@ page import="java.util.List" %>
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
    </style>
</head>

<body>
    <header>
        <img src="/images/UniXLogo.jpg" height="32px" />
        <h1>Course Enrolment</h1>
        <div class="icon-container" style="gap:20px">
            <div class="icon-container">
                <img src="/images/User.png" height="24px" />
                <p>
                    <%=session.getAttribute("username") %>
                </p>
            </div>
            <button class="icon-container" onclick="location.href='LogoutServlet'">
                <img src="/images/LogOut.png" height="24px" />
                Log Out</button>
        </div>
        </div>
    </header>


    <%
        Course enroledcourse = (Course) session.getAttribute("course");
        List<Course> currentEnrolments = (List<Course>) session.getAttribute("currentEnrolments");
    %>
    <div class="container">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            <h2>Course Enrolment Successful </h2>
            <hr>
            <p style="margin-bottom: 10px;">You have successfully enrolled in <span style="font-weight: bold;">
                    <%=enroledcourse.getCourseID()%> - <%=enroledcourse.getCourseName()%>
                </span>.</p>
        </div>
    </div>

    <div class="container">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            
            <h2>Current Enrolments</h2>

            <%  if(currentEnrolments!=null && !currentEnrolments.isEmpty()) { %>
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
            
            <div class="button-container" style="margin-top: 60px;">
                <form action="FindCourseServlet" method="GET">
                    <button type="submit" style="padding: 5px 15px;font-size: 16px;">Enrol Another Course</button>
                </form>
            </div>
        </div>

    </div>

</body>

</html>