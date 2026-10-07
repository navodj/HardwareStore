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
import com.mycompany.hardwarestore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User login(String username, String password) throws SQLException {

        String sql = """
                SELECT user_id, username, role
                FROM users
                WHERE username = ? AND password = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    int userId = result.getInt("user_id");
                    String dbUsername = result.getString("username");
                    String role = result.getString("role");

                    return new User(
                            userId,
                            dbUsername,
                            role
                    );
                }
            }
        }

        return null;
    }
}