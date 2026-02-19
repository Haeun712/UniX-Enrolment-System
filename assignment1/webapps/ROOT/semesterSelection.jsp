<!DOCTYPE html>
<html lang="en">

<head>
    <title>UniX - Enrolment</title>
    <link rel="stylesheet" href="/css/style.css">
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

    <div class="container">
        <div style="width: 80%; max-width: 600px;text-align: left;">
            <h2>Select a Semester</h2>
            <hr>
            <p style="margin-bottom: 10px;">Select a semester then click Continue.</p>
            <form action="SemesterSelectionServlet" method="post">
                <div style="margin-bottom: 60px;">
                    <label style="display: block;margin-bottom: 10px;">
                        <input type="radio" name="semester" value="Semester 1 - 2026" checked>
                        Semester 1 - 2026
                    </label>
                    <label style="display: block;">
                        <input type="radio" name="semester" value="Midyear session - 2026">
                        Midyear Session - 2026
                    </label>
                </div>
                <div class="button-container">
                    <button type="submit" style="padding: 5px 15px;font-size: 16px;">Continue</button>
                </div>
            </form>
        </div>
    </div>


    <%@ include file="/modal/error.jsp" %>
</body>

</html>