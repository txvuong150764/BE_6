package com.example.demo1.controller;

import com.example.demo1.DatabaseConnection;
import com.example.demo1.model.Product;
import com.example.demo1.repository.ProductRepo;
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
        ArrayList<Product> products = new ArrayList<>();
        String category = request.getParameter("category");
        ProductRepo productRepo = new ProductRepo();

        if(category != null) {

            products = productRepo.findAllProductsByCategory(category);
        }
        else {
            products = productRepo.findAllProducts();
        }
        request.setAttribute("products", products);
        RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
        rd.forward(request, response);
    }
}
