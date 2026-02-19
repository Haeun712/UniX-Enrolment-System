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


    <div class="container" style="margin-top: 22px;">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            <button class="icon-container" style="padding: 2px 2px;margin-bottom: 10px;border-radius: 100px; position: relative;
    left: -4px;" onclick="location.href='SemesterSelectionServlet'">
                <img src="/images/Arrow left.png" height="24px" /></button>
            <h2>Find Course</h2>
            <hr>
            <p style="margin-bottom: 10px;">Search for courses offered in Semester 1 - 2026 by Course Code</p>
            <form action="FindCourseServlet" method="post">
                <label for="coursecode">Course Code</label><br>
                <input type="text" id="coursecode" name="coursecode" placeholder="Enter Course Code (e.g. SENG2050)"
                    style="width: 50%; max-width: 250px">
                <button type="submit" style="padding: 5px 15px;font-size: 16px;margin-left: 20px;">Search</button>
            </form>
        </div>
    </div>

    <div class="container">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            <h2>Select a Course</h2>
            <hr>
            <p style="margin-bottom: 10px;">Select a course to enrol in semester 1 - 2026</p>
            <%@ include file="searchresults.jsp" %>
        </div>
    </div>

    <div class="container">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            <h2>Current Enrolments</h2>
            <table style="width: 100%; border: 1px solid #c5c5c5;border-collapse: collapse; margin-top: 20px;">
                <thead style="background-color: #c5c5c5;">
                    <tr>
                        <th>Course</th>
                        <th>Units</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>MATH2021 - Discrete Mathematics</td>
                        <td>10</td>
                        <td>Enrolled</td>
                    </tr>
                    <tr>
                        <td>COMP2020 - Data Structure</td>
                        <td>10</td>
                        <td>Enrolled</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="courseenrolment.jsp" %>

    <%@ include file="/modal/error.jsp" %>
</body>

</html>