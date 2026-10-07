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

    //getting all the products even inactive for admin
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
                Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet result = statement.executeQuery()) {

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

    //getting only active products for the customers
    public List<Product> getActiveProducts() throws SQLException {

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
            WHERE p.is_active = TRUE
            ORDER BY p.product_name
            """;

        try (
                Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet result = statement.executeQuery()) {

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

    public boolean addProduct(Product product) throws SQLException {

        String sql = """
            INSERT INTO products
            (category_id, product_name, description, price, stock_quantity, is_active)
            VALUES (?, ?, ?, ?, ?, TRUE)
            """;

        try (
                Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getStockQuantity());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        }
    }

    public boolean updateProduct(Product product) throws SQLException {

        String sql = """
            UPDATE products
            SET category_id = ?,
                product_name = ?,
                description = ?,
                price = ?,
                stock_quantity = ?
            WHERE product_id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getStockQuantity());

            statement.setInt(6, product.getProductId());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        }
    }

    public boolean deleteProduct(int productId) throws SQLException {

        String sql = """
            UPDATE products
            SET is_active = FALSE
            WHERE product_id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productId);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        }
    }

}
