package com.example.demo1.repository;

import com.example.demo1.DatabaseConnection;
import com.example.demo1.model.Comment;
import com.example.demo1.model.Product;

import java.sql.*;
import java.util.ArrayList;

public class CommentRepo {
    public void addComment(Comment comment) {
        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "INSERT INTO comments (id, createTime, detail, userId, productId) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, comment.getId());
                statement.setTimestamp(2, comment.getCreateTime());
                statement.setString(3, comment.getDetail());
                statement.setInt(4, comment.getUserId());
                statement.setInt(5, comment.getProductId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Comment> findAllCommentByProductId(int productId) {
        ArrayList<Comment> comments = new ArrayList<>();

        try(Connection connection = DatabaseConnection.initializeDatabaseConnection()) {
            String query = "SELECT * FROM comments WHERE productId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, productId);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("id");
                    Timestamp createTime = rs.getTimestamp("createTime");
                    String detail = rs.getString("detail");
                    int userId = rs.getInt("userId");

                    comments.add(new Comment(createTime, detail, userId, productId));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }
}
