<%@ page import="java.sql.Connection" %>
<%@ page import="com.example.demo1.DatabaseConnection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script>
        history.forward();
    </script>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username != null && password != null) {
            try {
                Connection connection = DatabaseConnection.initializeDatabaseConnection();

                String query = "SELECT id FROM users WHERE username = ? AND password = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();
                    if (!rs.next()) {
                        response.sendRedirect("login.jsp?error=true");
                    }
                    session.setAttribute("id", rs.getInt("id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    %>

    <h1>Welcome <%= username != null ? username : ""%></h1>
    <% if(session.getAttribute("id") == null) { %>
        <a href="login.jsp">Login</a>
    <% } %>
    <a href="product.jsp">Product</a>
    <% if(session.getAttribute("id") != null) { %>
        <a href="login.jsp?logout=true">Logout</a>
    <% } %>
    <br/>
</body>
</html>