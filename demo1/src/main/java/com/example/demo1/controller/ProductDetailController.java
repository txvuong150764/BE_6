package com.example.demo1.controller;

import com.example.demo1.DatabaseConnection;
import com.example.demo1.model.Comment;
import com.example.demo1.model.Product;
import com.example.demo1.repository.CommentRepo;
import com.example.demo1.repository.ProductRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/products/*")
public class ProductDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int productId = getProductId(request.getPathInfo()); // /{id}

        if (productId == -1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing");
            return;
        }

        ProductRepo productRepo = new ProductRepo();
        CommentRepo commentRepo = new CommentRepo();

        Product product = productRepo.findProductById(productId);
        ArrayList<Comment> comments = commentRepo.findAllCommentByProductId(productId);

        request.setAttribute("product", product);
        request.setAttribute("comments", comments);
        RequestDispatcher rd = request.getRequestDispatcher("/productDetail.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("remember-me".equals(cookie.getName())) {
                    CommentRepo commentRepo = new CommentRepo();
                    commentRepo.addComment(new Comment(
                            Timestamp.valueOf(LocalDateTime.now()),
                            request.getParameter("comment"),
                            (Integer) session.getAttribute("id"),
                            getProductId(request.getPathInfo()))
                            );
                    response.sendRedirect(request.getRequestURI());
                    return;
                }
            }
        }
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/login");
    }

    public int getProductId(String uri) {
        if (uri == null || uri.equals("/")) {
            return -1;
        }

        String productId = uri.substring(1); // Extract the ID part

        return Integer.parseInt(productId);
    }
}
