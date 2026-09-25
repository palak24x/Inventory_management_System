package com.inventory.model;

public class NonPerishableProduct extends Product {

    public NonPerishableProduct(int id, String name, double price, int quantity,
                                int reorderThreshold) {
        super(id, name, price, quantity, reorderThreshold, null);
    }

    @Override
    public boolean isReorderNeeded() {
        return getQuantity() <= getReorderThreshold();
    }
}
