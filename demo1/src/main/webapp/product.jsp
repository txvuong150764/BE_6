<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.example.demo1.DatabaseConnection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Tran Xuan Vuong
  Date: 20/07/2024
  Time: 2:23 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<style>
    table, th, td {
        border:1px solid black;
    }
</style>
<body>
    <h1>Product Page</h1>
    <%
        String username = null;
        if(session.getAttribute("id") != null) {
            int id = (int) session.getAttribute("id");
            try {
                Connection connection = DatabaseConnection.initializeDatabaseConnection();

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
    %>
    <p>This is product page. Hello <%= username != null ? username : "" %></p>

    <a href="products?category=food">Food</a>
    <a href="products?category=drink">Drink</a>
    <a href="products?category=fruit">Fruit</a>


    <table>
        <tr>
            <th>Product Name</th>
            <th>Price</th>
            <th>Detail</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td><a href="products/${product.id}">More</a></td>
            </tr>
        </c:forEach>
    </table>


    <a href="./homepage">Go back to homepage</a>


</body>
</html>
