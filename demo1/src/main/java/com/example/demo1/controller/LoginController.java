package com.example.demo1.controller;

import com.example.demo1.CookieUtils;
import com.example.demo1.DatabaseConnection;
import com.example.demo1.TokenGenerator;
import com.example.demo1.model.Cart;
import com.example.demo1.repository.CartRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String error = request.getParameter("error");
        String logout = request.getParameter("logout");
        String errorMessage = null;

        if (error != null) {
            errorMessage  = "Username or Password is incorrect";
        }
        if (logout != null) {
            session.removeAttribute("id");
            session.removeAttribute("username");

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (!"JSESSIONID".equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
        if (session != null && session.getAttribute("id") != null) {
            // User is logged in, redirect to homepage
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.sendRedirect(request.getContextPath() + "/homepage");
            return;
        }

        request.setAttribute("errorMessage", errorMessage);
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        HttpSession session = request.getSession();
        try {
            connection = DatabaseConnection.initializeDatabaseConnection();
            setRecentUserSession(session, connection, request);

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String token = TokenGenerator.generateToken();

            if (username != null && password != null) {
                if (validateUser(username, password, session, token, connection, response)) {
                    response.sendRedirect("./homepage");
                    return;
                } else {
                    response.sendRedirect("./login?error=True");
                    return;
                }
            } else {
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

        request.setAttribute("username", session.getAttribute("username"));
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }

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
        CartRepo cartRepo = new CartRepo();

        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                saveMostRecentUser(token, userId, connection);

                Cart cart = cartRepo.findUserId(userId);

                CookieUtils.addCookie("remember-me", token, response);
                if (cart == null) {
                    String cartId = TokenGenerator.generateToken();
                    CookieUtils.addCookie("cart-id", cartId, response);
                    cartRepo.addCart(new Cart(cartId, userId));
                }
                else {
                    CookieUtils.addCookie("cart-id", cart.getId(), response);
                }

                session.setAttribute("id", userId);
                return true;
            }
        }
        return false;
    }

    private void saveMostRecentUser(String token, int userId, Connection connection) throws SQLException {
        String query = "INSERT INTO most_recent_user (token, create_time, userId) VALUES (?, ?, ?)";
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
}
