package com.example.demo1.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Comment {
    private int id;
    private Timestamp createTime;
    private String detail;
    private int userId;
    private int productId;

    public Comment(Timestamp createTime, String detail, int userId, int productId) {
        this.createTime = createTime;
        this.detail = detail;
        this.userId = userId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
