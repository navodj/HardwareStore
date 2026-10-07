package com.mycompany.hardwarestore.service;

import com.mycompany.hardwarestore.model.CartItem;
import com.mycompany.hardwarestore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product, int quantity) {

        for (CartItem item : cartItems) {

            if (item.getProduct().getProductId()
                    == product.getProductId()) {

                int newQuantity = item.getQuantity() + quantity;

                item.setQuantity(newQuantity);

                return;
            }
        }

        CartItem item = new CartItem(product, quantity);
        cartItems.add(item);
    }
    
    public int getQuantityForProduct(int productId) {

    for (CartItem item : cartItems) {

        if (item.getProduct().getProductId() == productId) {
            return item.getQuantity();
        }
    }

    return 0;
}
}
