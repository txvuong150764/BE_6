package com.example.demo1;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static void addCookie(String name, String value, HttpServletResponse response) {
        System.out.println("add cookie");
        Cookie rememberMeCookie = new Cookie(name, value);
        rememberMeCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
        rememberMeCookie.setHttpOnly(true);
        response.addCookie(rememberMeCookie);
    }

    public static boolean isExisted(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Cookie getCookieByName(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }

        return null;
    }
}
