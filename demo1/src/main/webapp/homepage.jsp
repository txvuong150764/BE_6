<%@ page import="com.example.demo1.DatabaseConnection" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
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
        Connection connection = null;
        try {
            connection = DatabaseConnection.initializeDatabaseConnection();

            String query = "SELECT * FROM most_recent_user WHERE create_time = (SELECT max(create_time) FROM most_recent_user)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    session.setAttribute("id", rs.getInt("userId"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sessionId = session.getId();

        if(username != null && password != null) {
            try {
                String query = "SELECT id FROM users WHERE username = ? AND password = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();
                    if (!rs.next()) {
                        response.sendRedirect("login.jsp?error=true");
                    }

                    try (PreparedStatement statement1 = connection.prepareStatement("INSERT INTO most_recent_user (cookie, create_time, userId) VALUES (?, ?, ?)")) {
                        statement1.setString(1, sessionId);
                        statement1.setTimestamp(2, Timestamp.valueOf(java.time.LocalDateTime.now()));
                        statement1.setInt(3, rs.getInt("id"));
                        statement1.executeUpdate();
                    }
                    catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    session.setAttribute("id", rs.getInt("id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            if(session.getAttribute("id") != null) {
                int id = (int) session.getAttribute("id");
                try {
                    String query = "SELECT username FROM users WHERE id = ?";

                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, String.valueOf(id));
                        ResultSet rs = statement.executeQuery();
                        rs.next();
                        username = rs.getString("username");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    %>
    <p>Session Id: <%=sessionId%></p>
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