package com.example.demo1.controller;

import com.example.demo1.CookieUtils;
import com.example.demo1.DatabaseConnection;
import com.example.demo1.TokenGenerator;
import com.example.demo1.model.Cart;
import com.example.demo1.repository.CartProductRepo;
import com.example.demo1.repository.CartRepo;
import com.example.demo1.repository.UserRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

@WebServlet("/homepage")
public class HomepageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Requested URI: " + request.getRequestURI());

        UserRepo userRepo = new UserRepo();
        CartRepo cartRepo = new CartRepo();
        CartProductRepo cartProductRepo = new CartProductRepo();

        HttpSession session = request.getSession();
        if (session.getAttribute("id") != null) {
            int userId = (int) session.getAttribute("id");
            request.setAttribute("username", userRepo.getUsernameById(userId));
        }
        System.out.println("c");

        if (!CookieUtils.isExisted("cart-id", request)) {
            String token = TokenGenerator.generateToken();
            CookieUtils.addCookie("cart-id", token, response);
            cartRepo.addCart(new Cart(token));
        }
        System.out.println("b");
        Cookie cartCookie = CookieUtils.getCookieByName("cart-id", request);
        System.out.println(cartCookie.getValue());
        int cartSize = cartProductRepo.findByCartId(cartCookie.getValue()) == null ? 0 : cartProductRepo.findByCartId(cartCookie.getValue()).size();
        request.setAttribute("cartSize", cartSize);

        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
}

