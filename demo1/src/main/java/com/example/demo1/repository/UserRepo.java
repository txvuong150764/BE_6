package com.example.demo1.repository;

import com.example.demo1.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    public String getUsernameById(int id){
        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
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
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
