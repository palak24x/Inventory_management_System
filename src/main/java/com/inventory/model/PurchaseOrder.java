package com.inventory.model;

public class PurchaseOrder {
    private int productId;
    private int supplierId;
    private int quantityOrdered;

    public PurchaseOrder(int productId, int supplierId, int quantityOrdered) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.quantityOrdered = quantityOrdered;
    }

    public int getProductId() { return productId; }
    public int getSupplierId() { return supplierId; }
    public int getQuantityOrdered() { return quantityOrdered; }
}
