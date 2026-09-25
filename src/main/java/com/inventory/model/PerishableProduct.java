package com.inventory.model;

import java.time.LocalDate;

public class PerishableProduct extends Product {

    public PerishableProduct(int id, String name, double price, int quantity,
                             int reorderThreshold, LocalDate expiryDate) {
        super(id, name, price, quantity, reorderThreshold, expiryDate);
    }

    @Override
    public boolean isReorderNeeded() {
        if (getQuantity() <= getReorderThreshold()) {
            return true;
        }

        // Alert if the product expires within 7 days.
        if (getExpiryDate() != null &&
            !getExpiryDate().isAfter(LocalDate.now().plusDays(7))) {
            return true;
        }

        return false;
    }
}
