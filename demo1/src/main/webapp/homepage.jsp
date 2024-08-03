<%@ page import="com.example.demo1.DatabaseConnection" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.example.demo1.TokenGenerator" %>
<%@ page import="java.net.http.HttpRequest" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script>
        history.forward();
    </script>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<%
    Connection connection = null;
    try {
        connection = DatabaseConnection.initializeDatabaseConnection();

        // Check and set recent user session
        setRecentUserSession(session, connection, request);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = TokenGenerator.generateToken();

        if (username != null && password != null) {
            // Validate user and manage session
            if (validateUser(username, password, session, token, connection, response)) {
                response.sendRedirect("homepage.jsp");
                return;
            } else {
                response.sendRedirect("login.jsp?error=true");
                return;
            }
        } else {
            // Get username for existing session
            if (session.getAttribute("id") != null) {
                int id = (int) session.getAttribute("id");
                username = getUsernameById(id, connection);
                session.setAttribute("username", username);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
%>
    <h1>Welcome <%= session.getAttribute("username") != null ? session.getAttribute("username") : "" %></h1>
    <% if (session.getAttribute("id") == null) { %>
    <a href="login.jsp">Login</a>
    <% } %>
    <a href="product.jsp">Product</a>
    <% if (session.getAttribute("id") != null) { %>
    <a href="login.jsp?logout=true">Logout</a>
    <% } %>
    <br/>
    <a href="products?category=food">Food</a>
    <a href="products?category=drink">Drink</a>
    <a href="products?category=fruit">Fruit</a>

<%--    <c:forEach var="skill" items="${skillList}">--%>
<%--        <li>${skill}</li>--%>
<%--    </c:forEach>--%>
</body>
</html>

<%!
    private void setRecentUserSession(HttpSession session, Connection connection, HttpServletRequest request) throws SQLException {
        if (session.getAttribute("id") == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("remember-me".equals(cookie.getName())) {
                        String token = cookie.getValue();
                        int id = getUserIdFromToken(token, connection);
                        if (id != -1) {
                            session.setAttribute("id", id);
                        }
                        break;
                    }
                }
            }
        }
    }

    private boolean validateUser(String username, String password, HttpSession session, String token, Connection connection, HttpServletResponse response) throws SQLException {
        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                saveMostRecentUser(token, userId, connection);

                Cookie rememberMeCookie = new Cookie("remember-me", token);
                rememberMeCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
                rememberMeCookie.setHttpOnly(true);
                response.addCookie(rememberMeCookie);

                session.setAttribute("id", userId);
                return true;
            }
        }
        return false;
    }

    private void saveMostRecentUser(String token, int userId, Connection connection) throws SQLException {
        String query = "INSERT INTO most_recent_user (cookie, create_time, userId) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(3, userId);
            statement.executeUpdate();
        }
    }

    private String getUsernameById(int id, Connection connection) throws SQLException {
        String query = "SELECT username FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        }
        return null;
    }

    private int getUserIdFromToken(String token, Connection connection) throws SQLException {
        String query = "SELECT userId FROM most_recent_user WHERE token = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("userId");
            }
        }
        return -1;
    }
%>
