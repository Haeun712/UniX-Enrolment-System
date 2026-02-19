<!DOCTYPE html>
<html>
<!-- show modal if login failed -->
<% Boolean Error=(Boolean) request.getAttribute("Error"); %>
    <dialog id="error">
        <div style="display: flex;align-items: center;margin-bottom: 20px;">
            <img src="../images/Alert circle.png" height="20px" />
            <h4 style="margin-left: 10px;">
                <%= request.getAttribute("errorTitle")%>
            </h4>
        </div>
        <p style="margin-bottom: 20px">
            <%= request.getAttribute("errorContent")%>
        </p>
        <div class="button-container">
            <button commandfor="error" command="close">Close</button>
        </div>
    </dialog>

    <% if (Error !=null && Error) {%>
        <script>
            const dialog = document.getElementById("error");
            dialog.showModal();
        </script>
        <% } %>


</html>