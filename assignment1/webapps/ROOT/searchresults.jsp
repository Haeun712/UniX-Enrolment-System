<%@ page import="java.util.*" %>
    <!DOCTYPE html>
    <html>
    <style>
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
            background-color: #E6ECF7;
        }
    </style>
    <% // List (results) from Controller 
    List courses=(List) request.getAttribute("courses"); // objects 
    if(courses!=null && !courses.isEmpty()) { for (Object obj : courses) { Map course=(Map) obj; %>
        <form action="CourseEnrolmentServlet" method="get">
            <input type="hidden" name="coursecode" value="SENG2050" /><!--hardcoded-->
            <button class="resultbox" type="submit" onclick="toggleSidebar()">
                <div class="icon-container" style="margin-top: 5px;">
                    <p style="margin-right: 20px;color:#003AA6">
                        <%= course.get("code") %>
                    </p>
                    <% if((Boolean)course.get("open")) { %>
                        <span
                            style="display: inline-block; width: 10px; height: 10px; background-color: #00AF0F; border-radius: 50%;"></span>
                        <p>Open</p>
                        <% } else {%>
                            <span
                                style="display: inline-block; width: 10px; height: 10px; background-color: #c5c5c5; border-radius: 50%;"></span>
                            <p>Closed</p>
                            <% } %>
                </div>
                <p style="font-weight: bold;">
                    <%= course.get("name") %>
                </p>
            </button>
        </form>
        <% } %>
            <%} else {%>
                <p style="margin-top: 40px; font-weight: bold;">No courses found</p>
                <% } %>

    </html>