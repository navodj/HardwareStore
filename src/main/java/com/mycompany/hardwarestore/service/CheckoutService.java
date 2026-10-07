package com.mycompany.hardwarestore.service;

import com.mycompany.hardwarestore.config.DBConnection;
import com.mycompany.hardwarestore.model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

public class CheckoutService {

    public int checkout(
            int customerId,
            List<CartItem> cartItems,
            String paymentMethod
    ) throws SQLException {

        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot checkout an empty cart."
            );
        }
        double totalAmount = 0;

        for (CartItem item : cartItems) {
            totalAmount += item.getSubtotal();
        }

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();

            connection.setAutoCommit(false);

            String orderSql = """
        INSERT INTO orders
        (customer_id, total_amount, status)
        VALUES (?, ?, 'COMPLETED')
        """;

            int orderId;

            try (PreparedStatement statement
                    = connection.prepareStatement(
                            orderSql,
                            Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, customerId);
                statement.setDouble(2, totalAmount);

                statement.executeUpdate();

                try (ResultSet keys = statement.getGeneratedKeys()) {

                    if (keys.next()) {
                        orderId = keys.getInt(1);
                    } else {
                        throw new SQLException(
                                "Failed to retrieve order ID."
                        );
                    }
                }
            }

            String itemSql = """
        INSERT INTO order_items
        (order_id, product_id, quantity, unit_price, subtotal)
        VALUES (?, ?, ?, ?, ?)
        """;

            for (CartItem item : cartItems) {

                try (PreparedStatement statement
                        = connection.prepareStatement(itemSql)) {

                    statement.setInt(1, orderId);
                    statement.setInt(
                            2,
                            item.getProduct().getProductId()
                    );
                    statement.setInt(
                            3,
                            item.getQuantity()
                    );
                    statement.setDouble(
                            4,
                            item.getProduct().getPrice()
                    );
                    statement.setDouble(
                            5,
                            item.getSubtotal()
                    );

                    statement.executeUpdate();
                }
            }
            String stockSql = """
        UPDATE products
        SET stock_quantity = stock_quantity - ?
        WHERE product_id = ?
          AND stock_quantity >= ?
          AND is_active = TRUE
        """;

            for (CartItem item : cartItems) {

                try (PreparedStatement statement
                        = connection.prepareStatement(stockSql)) {

                    int quantity = item.getQuantity();

                    statement.setInt(1, quantity);
                    statement.setInt(
                            2,
                            item.getProduct().getProductId()
                    );
                    statement.setInt(3, quantity);

                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected == 0) {
                        throw new SQLException(
                                "Insufficient stock for "
                                + item.getProduct().getProductName()
                        );
                    }
                }
            }

            String paymentSql = """
        INSERT INTO payments
        (order_id, payment_method, amount, payment_status)
        VALUES (?, ?, ?, 'SUCCESS')
        """;

            try (PreparedStatement statement
                    = connection.prepareStatement(paymentSql)) {

                statement.setInt(1, orderId);
                statement.setString(2, paymentMethod);
                statement.setDouble(3, totalAmount);

                statement.executeUpdate();
            }

            // Checkout operations will go here
            connection.commit();

            return orderId;

        } catch (SQLException e) {

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }
            }

            throw e;
        } finally {

            if (connection != null) {
                connection.close();
            }
        }
    }
}
