package com.mycompany.hardwarestore.dao;

import com.mycompany.hardwarestore.config.DBConnection;
import com.mycompany.hardwarestore.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Order> getOrdersByCustomerId(int customerId)
            throws SQLException {

        List<Order> orders = new ArrayList<>();

        String sql = """
                SELECT
                    o.order_id,
                    o.order_date,
                    o.total_amount,
                    o.status,
                    p.payment_method
                FROM orders o
                LEFT JOIN payments p
                    ON o.order_id = p.order_id
                WHERE o.customer_id = ?
                ORDER BY o.order_date DESC
                """;

        try (
                Connection connection = DBConnection.getConnection(); PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    Order order = new Order(
                            result.getInt("order_id"),
                            result.getTimestamp("order_date"),
                            result.getDouble("total_amount"),
                            result.getString("status"),
                            result.getString("payment_method")
                    );

                    orders.add(order);
                }
            }
        }

        return orders;
    }

    public List<Object[]> getAllSales() throws SQLException {

        List<Object[]> sales = new ArrayList<>();

        String sql = """
            SELECT
                o.order_id,
                c.customer_name,
                o.order_date,
                o.total_amount,
                p.payment_method,
                o.status
            FROM orders o
            JOIN customers c
                ON o.customer_id = c.customer_id
            LEFT JOIN payments p
                ON o.order_id = p.order_id
            ORDER BY o.order_date DESC
            """;

        try (
                Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Object[] row = {
                    result.getInt("order_id"),
                    result.getString("customer_name"),
                    result.getTimestamp("order_date"),
                    result.getDouble("total_amount"),
                    result.getString("payment_method"),
                    result.getString("status")
                };

                sales.add(row);
            }
        }

        return sales;
    }

}
