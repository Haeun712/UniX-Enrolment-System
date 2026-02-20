<!DOCTYPE html>
<html>
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
        background-color: white;
        box-shadow: -8px 0 20px rgba(0, 0, 0, 0.10);
    }
</style>

<% String targetcourse=(String) request.getAttribute("targetcourse"); if (targetcourse!=null && !targetcourse.isEmpty())
    { %>
    <div class="sidebar" id="Sidebar" style="padding: 20px">
        <p style="margin-right: 20px;color:#003AA6">
            <%= targetcourse %>
        </p>

        <p style="font-weight: bold;">
            Web Engineering
        </p>



        <p style="margin-bottom: 20px;"><span
                style="display: inline-block; width: 10px; height: 10px; background-color: #00AF0F; border-radius: 50%;"></span>Open
        </p>

        <p>Location: Callaghan</p>
        <p>Units: 10</p>
        <p>Student Level: Undergraduate</p>
        <p style="margin-bottom: 10px;">Course Level: 2000-level</p>


        <p style="margin-bottom: 10px;">Prerequisite (must be completed)<br>SENG1050</p>
        <p>Assumed Knowledge<br>COMP1140 and SENG1110, or INFT1004 (or equivalent) </p>
        <div class="button-container">
            <form action="CourseEnrolmentServlet" method="post">
                <input type="hidden" name="course" value="SENG2050"/>
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
                        sidebar.style.right = "-250px"; // 닫기
                    } else {
                        sidebar.style.right = "0px";    // 열기
                    }
                }
                window.onload = function () {
                    setTimeout(toggleSidebar, 10);
                };

            </script>
            <% } %>

</html>