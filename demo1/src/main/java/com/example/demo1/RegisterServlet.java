package com.example.demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String hobbies = request.getParameter("hobbies");
        String city = request.getParameter("city");
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            // Hash the password before storing it

            // Connect to the DB and create table if it does not exist
            try (Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
                connection.createStatement()
                        .executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                + " username VARCHAR(50),"
                                + " password VARCHAR(100),"
                                + " gender VARCHAR(20),"
                                + " hobbies VARCHAR(100),"
                                + " city VARCHAR(50)"
                                + ");");

                // Save the new user record into the user table
                int rowsInserted = saveUserRecord(connection, username, password, gender, hobbies, city);

                // Check if user record is saved, return message to browser
                if (rowsInserted > 0) {
                    out.println("<h1>Thanks</h1>");
                    out.println("<p>Register successfully</p>");
                } else {
                    out.println("<h1>Sorry</h1>");
                    out.println("<p>Registration failed</p>");
                }
            } catch (SQLException e) {
                e.printStackTrace(out);
                out.println("<h1>Error</h1>");
                out.println("<p>Unable to register due to a database error. Please try again later.</p>");
            }
        }
    }

    private int saveUserRecord(Connection connection, String username, String password, String gender, String hobbies,
                               String city) throws SQLException {
        String query = "INSERT INTO users (username, password, gender, hobbies, city) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, gender);
            statement.setString(4, hobbies);
            statement.setString(5, city);
            return statement.executeUpdate();
        }
    }
}
