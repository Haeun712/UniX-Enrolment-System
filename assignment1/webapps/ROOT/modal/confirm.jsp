<!-- show modal if confirming enrolment -->
<% Boolean confirm=(Boolean) request.getAttribute("confirm"); %>
    <dialog id="confirm">
        <div style="display: flex;align-items: center;margin-bottom: 20px;">
            <h4>
                <%= request.getAttribute("confirmTitle")%>
            </h4>
        </div>
        <p style="margin-bottom: 20px">
            <%= request.getAttribute("confirmContent")%>
        </p>
        <div class="button-container">
            <form action="CourseEnrolmentServlet" method="post">
                <input type="hidden" name="action" value="enrol" />
                <% String courseToEnrol =(String) request.getAttribute("course"); %>
                <input type="hidden" name="courseID" value="<%= courseToEnrol  %>" />
                <button>Proceed</button>
            </form>
            <button style="color: black;background-color: white;border: 1px solid #003AA6;" commandfor="confirm"
                command="close">Cancel</button>
        </div>
    </dialog>

    <% if (confirm !=null && confirm) {%>
        <script>
            const dialog = document.getElementById("confirm");
            dialog.showModal();
        </script>
        <% } %>