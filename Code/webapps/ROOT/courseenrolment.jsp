<%@ page import="seng2050.Course" %>
    <style>
        .sidebar {
            height: 100%;
            width: 30%;
            min-width: 200px;
            max-width: 400px;
            position: fixed;
            top: 0px;
            border-left: 1px solid #c5c5c5;
            border-top: 1px solid #c5c5c5;
            padding-top: 20px;
            transition: 0.3s;
            margin-top: 74px;
            background-color: #F1F6FF;
            right: 0;
            transform: translateX(100%);
            transition: transform 0.3s ease;
        }
    </style>

    <script>
        function closeSidebar() {
            var sidebar = document.getElementById("Sidebar");
            sidebar.style.transform = "translateX(100%)";
            sidebar.style.boxShadow = "none";
        }
    </script>

    <!-- show sidebar with open motion or not -->
    <% Boolean showSidebar=(Boolean) request.getAttribute("showSidebar"); Boolean openSidebarAnim=(Boolean)
        request.getAttribute("openSidebar"); if (showSidebar !=null && showSidebar) { if(openSidebarAnim !=null &&
        openSidebarAnim) { %>
        <script>
            function openSidebar() {
                var sidebar = document.getElementById("Sidebar");
                sidebar.style.transform = "translateX(0)";
                sidebar.style.boxShadow = "-4px 0 20px rgba(0, 0, 0, 0.08)";
            }

            window.onload = function () {
                openSidebar();
            };
        </script>
        <% } else {%>
            <style>
                .sidebar {
                    transform: translateX(0);
                    transition: none;
                    box-shadow: -4px 0 20px rgba(0, 0, 0, 0.08);
                }
            </style>
            <% } %>
                <% } %>

                    <% Course targetCourse=(Course) session.getAttribute("targetCourse"); if (targetCourse!=null) { %>
                        <div class="sidebar" id="Sidebar" style="padding: 20px">
                            <div style="display: flex; justify-content: space-between;align-items: end;">
                                <p style="margin-right: 20px;color:#003AA6; ">
                                    <%= targetCourse.getCourseID() %>
                                </p>
                                <button class="icon-container"
                                    style="padding: 2px 2px;border-radius: 100px;background-color: #F1F6FF;"
                                    onclick="closeSidebar()">
                                    <img src="/images/X.png" height="24px" /></button>

                            </div>
                            <p style="font-weight: bold;">
                                <%= targetCourse.getCourseName() %>
                            </p>
                            <p style="margin-bottom: 30px;font-size: 14px;">
                                <%= targetCourse.getCredits() %> Credits
                            </p>

                            <p style="font-weight: bold; margin-bottom: 5px;">Prerequisite <br><span
                                    style="font-size: 14px">(must be
                                    completed)</span></p>
                            <p style="font-size: 14px;">
                                <% List<String> PrerequisiteCourses = (List<String>)
                                        session.getAttribute("prerequisite");
                                        if (PrerequisiteCourses == null || PrerequisiteCourses.isEmpty()) {
                                        out.print("No prerequisite listed");
                                        } else {
                                        out.print(String.join(", ", PrerequisiteCourses));
                                        }
                                %>
                            </p>
                            <p style="font-weight: bold;margin-top: 30px; margin-bottom: 5px">Assumed Knowledge</p>
                            <p style="font-size: 14px;">
                                <% List<String> assumedKnowCourses = (List<String>)
                                        session.getAttribute("assumedknowledge");
                                        if (assumedKnowCourses == null || assumedKnowCourses.isEmpty()) {
                                        out.print("No assumed knowledge listed");
                                        } else {
                                        out.print(String.join(", ", assumedKnowCourses));
                                        }
                                %>
                            </p>
                            <div class="button-container">
                                <form action="CourseEnrolmentServlet" method="post">
                                    <input type="hidden" name="action" value="confirm" />
                                    <input type="hidden" name="courseID" value="<%= targetCourse.getCourseID()%>" />
                                    <button type="submit"
                                        style="padding: 6px 20px;font-size: 16px;margin-top: 40px;">Enrol</button>
                                </form>
                            </div>
                        </div>
                        <% } %>