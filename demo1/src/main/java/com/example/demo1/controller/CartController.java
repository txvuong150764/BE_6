package com.example.demo1.controller;

import com.example.demo1.CookieUtils;
import com.example.demo1.TokenGenerator;
import com.example.demo1.model.Cart;
import com.example.demo1.model.CartProduct;
import com.example.demo1.repository.CartProductRepo;
import com.example.demo1.repository.CartRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartRepo cartRepo = new CartRepo();
        CartProductRepo cartProductRepo = new CartProductRepo();
        HttpSession session = request.getSession();
        String productId = request.getParameter("productId");
        String quantity = request.getParameter("quantity");

        Cookie cartCookie = CookieUtils.getCookieByName("cart-id", request);
        cartProductRepo.addCartProduct(new CartProduct(cartCookie.getValue(), Integer.parseInt(productId), Integer.parseInt(quantity)));

        response.sendRedirect("./products");
    }
}
