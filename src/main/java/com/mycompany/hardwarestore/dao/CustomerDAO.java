package com.mycompany.hardwarestore.dao;

import com.mycompany.hardwarestore.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    public int getCustomerIdByUserId(int userId) throws SQLException {

        String sql = """
                SELECT customer_id
                FROM customers
                WHERE user_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, userId);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return result.getInt("customer_id");
                }
            }
        }

        return -1;
    }
}