package com.inventory.service;

import com.inventory.dao.DBConnection;
import com.inventory.exception.InvalidQuantityException;
import com.inventory.exception.ProductNotFoundException;
import com.inventory.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesService {
    private final InventoryService inventoryService;

    public SalesService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void recordSale(int productId, int quantity)
            throws SQLException, ProductNotFoundException, InvalidQuantityException {

        Product product = inventoryService.findProduct(productId);

        inventoryService.reduceStock(productId, quantity);

        String sql = "INSERT INTO sales(product_id, quantity_sold) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, product.getId());
            ps.setInt(2, quantity);
            ps.executeUpdate();
        }
    }
}
