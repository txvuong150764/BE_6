package com.example.demo1.model;

public class Cart {
    private String id;
    private int userId;

    public Cart(String id) {
        this.id = id;
    }

    public Cart(String id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
