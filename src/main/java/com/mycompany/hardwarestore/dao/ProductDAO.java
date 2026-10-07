package com.mycompany.hardwarestore.dao;

import com.mycompany.hardwarestore.config.DBConnection;
import com.mycompany.hardwarestore.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> getAllProducts() throws SQLException {

    List<Product> products = new ArrayList<>();

    String sql = """
            SELECT
                p.product_id,
                p.category_id,
                c.category_name,
                p.product_name,
                p.description,
                p.price,
                p.stock_quantity,
                p.is_active
            FROM products p
            JOIN categories c
                ON p.category_id = c.category_id
            ORDER BY p.product_id
            """;

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery()
    ) {

        while (result.next()) {

            Product product = new Product(
                    result.getInt("product_id"),
                    result.getInt("category_id"),
                    result.getString("category_name"),
                    result.getString("product_name"),
                    result.getString("description"),
                    result.getDouble("price"),
                    result.getInt("stock_quantity"),
                    result.getBoolean("is_active")
            );

            products.add(product);
        }
    }

    return products;
}
}