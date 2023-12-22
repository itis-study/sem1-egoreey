<%--
  Created by IntelliJ IDEA.
  User: guest
  Date: 30.10.2023
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Notion</title>
    <link type="text/css" rel="stylesheet" href="style.css">
</head>
<body>
<div class="sign_up">
    <h1>Registration</h1>
    <div class="sign_up_info">
        <form method="post" action="">
            <h3>Name</h3>
            <label>
                <input class="input_name" name="name" type="text" placeholder="Name" required>
            </label>
            <h3>Surname</h3>
            <label>
                <input class="input_surname" name="surname" type="text" placeholder="Surname" required>
            </label>
            <h3>Email</h3>
            <label>
                <input class="input_email" name="email" type="text" placeholder="Email" required>
            </label>
            <h3>Password</h3>
            <label>
                <input class="password_label" name="password" type="password" placeholder="Password" required>
            </label>
            <h3>Repeat password</h3>
            <label>
                <input class="repeat_password_label" name="repeat_password" type="password" placeholder="Password" required>
            </label>
            <div class="input_remember_me">
                <input class="checkbox_input" type="checkbox" required>
                <p>I accept all terms & condition</p>
            </div>
            <div class="sign_up_button">
                <input class="sign_up_input" name="sign_up_click" type="submit" value="Sign up" required>
            </div>
        </form>

    </div>
</div>
</body>
</html>
