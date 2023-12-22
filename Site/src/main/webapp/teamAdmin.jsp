<%@ page import="java.util.List" %>
<%@ page import="org.example.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team</title>
    <meta charset="UTF-8" />
    <script src="myTeam.js"></script>
    <link type="text/css" rel="stylesheet" href="style.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div class="my_team">
    <h1>My team</h1>
    <div class="my_team_info">
        <c:forEach items="${users}" var="user">
            <p>Name: ${user.name}</p>
            <p>Email: ${user.email}</p>
        </c:forEach>
        <form>
            <select id="email_to_delete">
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    for (User o:users) {
                        out.println("<option>" + o.getEmail() + "</option>");
                    }
                %>
            </select>
            <button id="delete" onclick="onClickDeleteUserFromTeam()">Delete</button>
        </form>
    </div>
    <div>
        <form>
            <input id="email_to_add" type="text" placeholder="Enter email" required>
            <button id="add" onclick="onClickToAddUserToTeam()">Add</button>
        </form>
    </div>
</div>
</body>
</html>
