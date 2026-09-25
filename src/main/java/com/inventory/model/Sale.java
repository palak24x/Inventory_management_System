package com.inventory.model;

public class Sale {
    private int productId;
    private int quantitySold;

    public Sale(int productId, int quantitySold) {
        this.productId = productId;
        this.quantitySold = quantitySold;
    }

    public int getProductId() { return productId; }
    public int getQuantitySold() { return quantitySold; }
}
