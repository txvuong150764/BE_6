package com.example.demo1;

import com.example.demo1.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String category = request.getParameter("category");

        if(category != null) {
            ArrayList<Product> products = new ArrayList<>();

            try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
                String query = "SELECT * FROM products WHERE category = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, category);
                    ResultSet rs = statement.executeQuery();
                    while(rs.next()) {
                        String productName = rs.getString("name");
                        float price = rs.getFloat("price");

                        products.add(new Product(productName, price));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
            rd.forward(request, response);
        }
    }
}
