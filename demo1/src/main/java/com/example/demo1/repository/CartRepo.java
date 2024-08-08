package com.example.demo1.repository;

import com.example.demo1.DatabaseConnection;
import com.example.demo1.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRepo {
    public void addCart(Cart cart) {
        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "INSERT INTO carts (id, userId) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cart.getId());
                statement.setInt(2, cart.getUserId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Cart findCartById(String id) {
        Cart cart = null;

        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "SELECT * FROM carts WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    String cartId = rs.getString("id");
                    cart = new Cart(cartId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cart;
    }

    public Cart findUserId(int id) {
        Cart cart = null;

        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "SELECT * FROM carts WHERE userId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    String cartId = rs.getString("id");
                    int userId = rs.getInt("userId");
                    cart = new Cart(cartId, userId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cart;
    }
}
