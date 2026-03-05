<%@ page import="seng2050.Course" %>
<style>
    .sidebar {
        height: 100%;
        width: 30%;
        min-width: 200px;
        max-width: 400px;
        position: fixed;
        top: 0px;
        right: -250px;
        border-left: 1px solid #c5c5c5;
        border-top: 1px solid #c5c5c5;
        padding-top: 20px;
        transition: 0.3s;
        margin-top: 74px;
        background-color: #F1F6FF;
        box-shadow: -8px 0 20px rgba(0, 0, 0, 0.10);
    }
</style>

<% Course targetCourse=(Course) request.getAttribute("targetCourse"); if (targetCourse!=null)
    { %>
    <div class="sidebar" id="Sidebar" style="padding: 20px">
        <div style="display: flex; justify-content: space-between;align-items: end;">
            <p style="margin-right: 20px;color:#003AA6; ">
                <%= targetCourse.getCourseID() %>
            </p>
            <button class="icon-container" style="padding: 2px 2px;border-radius: 100px;background-color: #F1F6FF;" onclick="location.href='FindCourseServlet'">
                    <img src="/images/X.png" height="24px" /></button>
        
        </div>
        <p style="font-weight: bold;">
            <%= targetCourse.getCourseName() %>
        </p>
        <p style="margin-bottom: 30px;font-size: 14px;"><%= targetCourse.getCredits() %> Credits</p>
        
        <p style="font-weight: bold; margin-bottom: 5px;">Prerequisite <br><span style="font-size: 14px">(must be completed)</span></p>
        <p style="font-size: 14px;"><%=request.getAttribute("prerequisite")%></p>
        <p style="font-weight: bold;margin-top: 30px; margin-bottom: 5px">Assumed Knowledge</p>
        <p style="font-size: 14px;"><%=request.getAttribute("assumedknowledge")%></p>
        <div class="button-container">
            <form action="CourseEnrolmentServlet" method="post">
                <input type="hidden" name="action" value="confirm" />
                <input type="hidden" name="courseID" value="<%= targetCourse.getCourseID()%>"/>
                <button type="submit" style="padding: 5px 15px;font-size: 16px;margin-top: 40px;">Enrol</button>
            </form>
        </div>
    </div>
    <% } %>
    
        <% Boolean openSidebar=(Boolean) request.getAttribute("openSidebar"); if(openSidebar !=null && openSidebar) { %>
            <script>
                function toggleSidebar() {
                    var sidebar = document.getElementById("Sidebar");
                    if (sidebar.style.right === "0px") {
                        sidebar.style.right = "-250px"; // Close
                    } else {
                        sidebar.style.right = "0px";    // Open
                    }
                }
                window.onload = function () {
                    setTimeout(toggleSidebar, 10);
                };

            </script>
            <% } %>

