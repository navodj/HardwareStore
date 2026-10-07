package com.mycompany.hardwarestore.dao;

import com.mycompany.hardwarestore.config.DBConnection;
import com.mycompany.hardwarestore.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<Category> getAllCategories() throws SQLException {

        List<Category> categories = new ArrayList<>();

        String sql = """
                SELECT category_id, category_name
                FROM categories
                ORDER BY category_name
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
        ) {

            while (result.next()) {

                Category category = new Category(
                        result.getInt("category_id"),
                        result.getString("category_name")
                );

                categories.add(category);
            }
        }

        return categories;
    }
}