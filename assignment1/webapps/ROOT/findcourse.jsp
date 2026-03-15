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
                                        <%=student.getGivenNames() %>
                                            <%=student.getLastName() %>
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
                                <p style="margin-bottom: 30px;">Search for courses offered in Semester
                                    <%=semester.getSemester() %> - <%=semester.getYear()%> by Course Code</p>
                                <form action="FindCourseServlet" method="get">
                                    <label for="search">Course Code</label><br>
                                    <% String searchString = (String) request.getAttribute("search") != null ? (String) request.getAttribute("search") : ""; %>
                                        <input type="text" id="search" name="search"
                                        placeholder="Enter Course Code (e.g. SENG2050)"
                                        value="<%= searchString %>"
                                        style="width: 50%; max-width: 250px; height: 30px; margin-top: 8px"
                                        maxlength="10">
                                    <button type="submit"
                                        style="padding: 6px 20px;font-size: 16px;margin-left: 20px;">Search</button>
                                </form>
                        </div>
                    </div>

                    <div class="container">
                        <div style="width: 80%; max-width: 600px;text-align: left;">
                            <h2>Select a Course</h2>
                            <hr>
                            <p style="margin-bottom: 10px;">Select a course to enrol in Semester
                                <%=semester.getSemester() %> - <%=semester.getYear()%>
                            </p>
                            <% Boolean showSearchResult = (Boolean) session.getAttribute("showSearchResult");
                                if(showSearchResult!=null && showSearchResult) {
                                    List<Course> courses=(List<Course>) session.getAttribute("searchresults");
                                    if(courses!=null && !courses.isEmpty()) {
                                    for (Course course : courses) { %>
                                    <form action="CourseEnrolmentServlet" method="get">
                                        <input type="hidden" name="coursecode" value="<%=course.getCourseID() %>" />
                                        <button class="resultbox" type="submit">
                                            <span style="margin-bottom: 5px;color:#003AA6; display: inline-block;">
                                                <%= course.getCourseID() %>
                                            </span><br>
                                            <span style="font-weight: bold; display: inline-block;">
                                                <%= course.getCourseName() %>
                                            </span>
                                        </button>
                                    </form>
                                    <% } %>
                                        <%} else {%>
                                            <p style="margin-top: 40px; font-weight: bold;">No courses found</p>
                                            <% } %>
                                <%} else { %>
                                    <p style="margin-top: 40px; font-weight: bold;">Please enter a course code and click Search to find courses.</p>
                                    <% } %>
                        </div>
                    </div>

                    <div class="container">
                        <div style="width: 80%; max-width: 600px;text-align: left;">
                            <h2>Current Enrolments</h2>
                            <% List<Course> currentEnrolments = (List<Course>)
                                    session.getAttribute("currentEnrolments");
                                    if(currentEnrolments!=null && !currentEnrolments.isEmpty()) { %>
                                    <table
                                        style="width: 100%; border: 1px solid #c5c5c5;border-collapse: collapse; margin-top: 20px;">
                                        <thead style="background-color: #c5c5c5;">
                                            <tr>
                                                <th>CourseID</th>
                                                <th>Course Name</th>
                                                <th>Credits</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (Course course : currentEnrolments) { %>
                                                <tr>
                                                    <td>
                                                        <%=course.getCourseID() %>
                                                    </td>
                                                    <td>
                                                        <%=course.getCourseName() %>
                                                    </td>
                                                    <td>
                                                        <%=course.getCredits() %>
                                                    </td>
                                                    <td style="text-align: center; width: 100px;">
                                                        <form action="CourseEnrolmentServlet" method="post">
                                                            <input type="hidden" name="action" value="confirmDrop" />
                                                            <input type="hidden" name="courseID"
                                                                value="<%= course.getCourseID() %>" />
                                                            <button type="submit"
                                                                style="background-color: #c50000;">
                                                                 &ndash;<span style="font-weight: bold;">  Drop</span>
                                                            </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <% } %>
                                        </tbody>
                                    </table>
                                    <%} else {%>
                                        <p style="margin-top: 40px; font-weight: bold;">You are not enrolled in any
                                            courses this semester.</p>
                                        <% } %>
                        </div>
                    </div>
                    <%@ include file="courseenrolment.jsp" %>

                        <%@ include file="/modal/error.jsp" %>
                            <%@ include file="/modal/warning.jsp" %>
                                <%@ include file="/modal/confirm.jsp" %>
                </body>

                </html>