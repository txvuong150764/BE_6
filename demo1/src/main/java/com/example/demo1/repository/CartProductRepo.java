package com.example.demo1.repository;

import com.example.demo1.DatabaseConnection;
import com.example.demo1.model.Cart;
import com.example.demo1.model.CartProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartProductRepo {
    public void addCartProduct(CartProduct cartProduct) {
        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "INSERT INTO cart_product (cartId, productId, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cartProduct.getCartId());
                statement.setInt(2, cartProduct.getProductId());
                statement.setInt(3, cartProduct.getQuantity());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<CartProduct> findByCartId(String id) {
        ArrayList<CartProduct> cartProducts = new ArrayList<>();

        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "SELECT * FROM cart_product WHERE cartId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    String cartId = rs.getString("cartId");
                    int productId = rs.getInt("productId");
                    int quantity = rs.getInt("quantity");
                    cartProducts.add(new CartProduct(cartId, productId, quantity));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cartProducts;
    }
}
