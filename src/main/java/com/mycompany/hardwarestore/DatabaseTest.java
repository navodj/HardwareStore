/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hardwarestore;

import com.mycompany.hardwarestore.config.DBConnection;
import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        try (Connection connection = DBConnection.getConnection()) {

            System.out.println("DATABASE CONNECTION SUCCESSFUL!");

        } catch (Exception e) {

            System.out.println("DATABASE CONNECTION FAILED!");

            e.printStackTrace();
        }
    }
}