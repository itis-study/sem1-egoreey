<%--
  Created by IntelliJ IDEA.
  User: guest
  Date: 30.10.2023
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <link type="text/css" rel="stylesheet" href="style.css">
</head>
<body>
<div class="sign_in">
    <h1>Notion</h1>
    <div class="sign_in_info">
        <form method="post" action="">
            <h3>Email</h3>
            <div class="email_label">
                <label>
                    <input class="input_email" name="email" type="text" placeholder="Email" required>
                </label>
            </div>
            <h3>Password</h3>
            <label>
                <input class="password_label" name="password" type="password" placeholder="Password" required>
            </label>
            <div class="sign_in_button">
                <input class="sign_in_input" name="sign_in_click" type="submit" value="Sign in" required>
            </div>
        </form>

    </div>
</div>
</body>
</html>
