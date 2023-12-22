<%@ page import="org.example.entity.Team" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Teams</title>
    <link type="text/css" rel="stylesheet" href="style.css">
    <script src="myTeam.js"></script>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div class="my_team">
    <h1>My teams</h1>
    <div class="my_team_info">
        <form method="post">
            <select name="team_name">
                <%
                    List<Team> teams = (List<Team>) request.getAttribute("teams");
                    for (Team o:teams) {
                        out.println("<option>" + o.getName() + "</option>");
                    }
                %>
            </select>
            <button id="show">Show</button>
        </form>
    </div>
</div>
</body>
</html>
