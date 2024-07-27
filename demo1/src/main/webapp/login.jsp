<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Tran Xuan Vuong
  Date: 20/07/2024
  Time: 1:32 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    session = request.getSession(false);

    String logout = request.getParameter("logout");
    if (logout != null) {
        session.removeAttribute("id");
    }

    session = request.getSession(true);

    if (session != null && session.getAttribute("id") != null) {
        // User is logged in, redirect to homepage
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.sendRedirect(request.getContextPath() + "/homepage.jsp");
        return;
    }
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String error = request.getParameter("error");

        if (error != null) {
            PrintWriter out1 = response.getWriter();
            out1.println("<h1>" + "Username or Password is incorrect" + "</h1>");
        }
    %>
    <form action="homepage.jsp" method="post">
        <h1>Login</h1>
        <br/>
        <label for="username">Username</label>
        <input type="text" name="username" id="username" placeholder="Username" required>
        <br/>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" placeholder="Password" required>

        <button type="submit">Login</button>
    </form>
    <a href="register.html">Register</a>
</body>
</html>