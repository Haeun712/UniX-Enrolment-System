
<!-- show modal if warning exist -->
<% Boolean warning=(Boolean) request.getAttribute("warning"); %>
    <dialog id="warning">
        <div style="display: flex;align-items: center;margin-bottom: 20px;">
            <img src="../images/Alert triangle.png" height="20px" />
            <h4 style="margin-left: 10px;">
                <%= request.getAttribute("warningTitle")%>
            </h4>
        </div>
        <p style="margin-bottom: 20px">
            <%= request.getAttribute("warningContent")%>
        </p>
        <div class="button-container">
            <form action="CourseEnrolmentServlet" method="post">
                <input type="hidden" name="action" value="enrol" />
                <% String warningCourseID =(String) request.getAttribute("course"); %>
                <input type="hidden" name="courseID" value="<%= warningCourseID  %>" />
                <button>Proceed</button>
            </form>
            <button style="color: black;background-color: white;border: 1px solid #003AA6;" commandfor="warning" command="close">
                Cancel
            </button>
        </div>
    </dialog>

    <% if (warning !=null && warning) {%>
        <script>
            const dialog = document.getElementById("warning");
            dialog.showModal();
        </script>
        <% } %>


