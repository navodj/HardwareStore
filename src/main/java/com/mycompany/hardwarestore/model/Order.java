package com.mycompany.hardwarestore.model;

import java.sql.Timestamp;

public class Order {

    private int orderId;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;
    private String paymentMethod;

    public Order(
            int orderId,
            Timestamp orderDate,
            double totalAmount,
            String status,
            String paymentMethod) {

        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public int getOrderId() {
        return orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}