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
  <h1>Create Team</h1>
  <div class="create_team_info">
    <form method="post">
      <label>
        <input type="text" class="team_name" name="team_name" placeholder="Team name" required>
      </label>
      <label>
        <input type="text" class="email_1" name="email_1" placeholder="Teammate 1" required>
      </label>
      <label>
        <input type="text" class="email_2" name="email_1" placeholder="Teammate 2">
      </label>
      <label>
        <input type="text" class="email_3" name="email_1" placeholder="Teammate 3">
      </label>
      <label>
        <input type="text" class="email_4" name="email_1" placeholder="Teammate 4">
      </label>
      <label>
        <input type="text" class="email_5" name="email_1" placeholder="Teammate 5">
      </label>
      <div class="sign_in_button">
        <input class="sign_in_input" name="sign_in_click" type="submit" value="Send" required>
      </div>
    </form>
  </div>
</div>
</body>
</html>
