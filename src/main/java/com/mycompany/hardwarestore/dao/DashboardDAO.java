/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hardwarestore.dao;

/**
 *
 * @author navod
 */

import com.mycompany.hardwarestore.config.DBConnection;
import com.mycompany.hardwarestore.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO {

    public int getTotalProducts() throws SQLException {

        String sql = """
                SELECT COUNT(*) AS total
                FROM products
                WHERE is_active = TRUE
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
        ) {

            if (result.next()) {
                return result.getInt("total");
            }
        }

        return 0;
    }


    public int getTotalSales() throws SQLException {

        String sql = """
                SELECT COUNT(*) AS total
                FROM orders
                WHERE status = 'COMPLETED'
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
        ) {

            if (result.next()) {
                return result.getInt("total");
            }
        }

        return 0;
    }


    public double getTotalRevenue() throws SQLException {

        String sql = """
                SELECT COALESCE(SUM(total_amount), 0) AS revenue
                FROM orders
                WHERE status = 'COMPLETED'
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
        ) {

            if (result.next()) {
                return result.getDouble("revenue");
            }
        }

        return 0;
    }
    
//    public ResultSet getLowStockProducts() throws SQLException {
//
//    String sql = """
//            SELECT product_name, stock_quantity
//            FROM products
//            WHERE stock_quantity <= 10
//            AND is_active = TRUE
//            ORDER BY stock_quantity ASC
//            """;
//
//    Connection connection = DBConnection.getConnection();
//
//    PreparedStatement statement =
//            connection.prepareStatement(sql);
//
//    return statement.executeQuery();
//}
    public List<Product> getLowStockProducts() throws SQLException {

    List<Product> products = new ArrayList<>();

    String sql = """
            SELECT *
            FROM products
            WHERE stock_quantity <= 10
            AND is_active = TRUE
            ORDER BY stock_quantity ASC
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