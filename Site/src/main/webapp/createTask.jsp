<%@ page import="org.example.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: egors
  Date: 18.11.2023
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task</title>
</head>
<body>
<%--
  Created by IntelliJ IDEA.
  User: guest
  Date: 04.11.2023
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Notion</title>
    <link type="text/css" rel="stylesheet" href="style.css">
</head>
<body>
<div class="create_team">
    <h1>Create Task</h1>
    <div class="create_team_info">
        <form method="post">
            <label>
                <input type="text" class="team_name" name="team_name" placeholder="Description" required>
            </label>
                <select id="teamsdffg">
                    <%
                        List<User> users = (List<User>) request.getAttribute("team_users");
                        for (User o:users) {
                            out.println("<option>" + o.getEmail() + "</option>");
                        }
                    %>
                </select>
            <div class="sign_in_button">
                <input class="sign_in_input" name="sign_in_click" type="submit" value="Send">
            </div>
        </form>
    </div>
</div>
</body>
</html>
</body>
</html>
