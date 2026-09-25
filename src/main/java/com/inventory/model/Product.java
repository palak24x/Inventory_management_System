package com.inventory.model;

import java.time.LocalDate;

public abstract class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int reorderThreshold;
    private LocalDate expiryDate;

    public Product(int id, String name, double price, int quantity,
                   int reorderThreshold, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
        this.expiryDate = expiryDate;
    }

    public abstract boolean isReorderNeeded();

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getReorderThreshold() { return reorderThreshold; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return id + " | " + name + " | ₹" + price + " | Stock: " + quantity;
    }
}
