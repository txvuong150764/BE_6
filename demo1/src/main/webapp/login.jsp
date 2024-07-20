<%--
  Created by IntelliJ IDEA.
  User: Tran Xuan Vuong
  Date: 20/07/2024
  Time: 1:32 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="homepage.jsp" method="post">
        <h1>Login</h1>
        <br/>
        <label for="username">Username</label>
        <input type="text" name="username" id="username" placeholder="Username">
        <br/>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" placeholder="Password">

        <button type="submit">Login</button>
    </form>
    <a href="register.html">Register</a>
</body>
</html>
