package com.example.demo1.repository;

import com.example.demo1.DatabaseConnection;
import com.example.demo1.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepo {
    public ArrayList<Product> findAllProductsByCategory(String category) {
        ArrayList<Product> products = new ArrayList<>();

        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "SELECT * FROM products WHERE category = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, category);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String productName = rs.getString("name");
                    float price = rs.getFloat("price");
                    String description = rs.getString("description");

                    products.add(new Product(id, productName, price, description));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    public ArrayList<Product> findAllProducts() {
        ArrayList<Product> products = new ArrayList<>();

        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "SELECT * FROM products";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String productName = rs.getString("name");
                    float price = rs.getFloat("price");
                    String description = rs.getString("description");

                    products.add(new Product(id, productName, price, description));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    public Product findProductById(int id) {
        Product product = null;

        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "SELECT * FROM products WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    int productId = rs.getInt("id");
                    String productName = rs.getString("name");
                    float price = rs.getFloat("price");
                    String description = rs.getString("description");
                    product = new Product(productId, productName, price, description);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }
}
