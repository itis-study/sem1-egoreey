<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team</title>
    <meta charset="UTF-8" />
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
    </div>
</div>
</body>
</html>
