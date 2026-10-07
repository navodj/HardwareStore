/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hardwarestore.service;

/**
 *
 * @author navod
 */

import com.mycompany.hardwarestore.dao.UserDAO;
import com.mycompany.hardwarestore.model.User;

import java.sql.SQLException;

public class AuthService {

    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public User login(String username, String password)
            throws SQLException {

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Username is required."
            );
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException(
                    "Password is required."
            );
        }

        return userDAO.login(
                username.trim(),
                password
        );
    }
}
