package com.example.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "be6";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "userroot";

    public static Connection initializeDatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }

        try (Connection initialConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            initialConnection.createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Database is created or already exists.");
        }

        Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
        System.out.println("Connected to the database " + DB_NAME);
        return connection;
    }
}
