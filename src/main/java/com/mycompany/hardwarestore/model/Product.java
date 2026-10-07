package com.mycompany.hardwarestore.model;

public class Product {

    private int productId;
    private int categoryId;
    private String categoryName;
    private String productName;
    private String description;
    private double price;
    private int stockQuantity;
    private boolean active;


    // Empty constructor
    public Product() {
    }


    // Constructor WITH category name
    // We will use this for Product Management
    public Product(
            int productId,
            int categoryId,
            String categoryName,
            String productName,
            String description,
            double price,
            int stockQuantity,
            boolean active) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.active = active;
    }


    // Constructor WITHOUT category name
    // Existing DashboardDAO can continue using this
    public Product(
            int productId,
            int categoryId,
            String productName,
            String description,
            double price,
            int stockQuantity,
            boolean active) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.active = active;
    }


    public int getProductId() {
        return productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public boolean isActive() {
        return active;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}