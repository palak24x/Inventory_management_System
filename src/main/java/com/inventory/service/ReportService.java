package com.inventory.service;

import com.inventory.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ReportService {
    private final InventoryService inventoryService;

    public ReportService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void showLowStockReport() throws SQLException {
        List<Product> products = inventoryService.getLowStockProducts();

        System.out.println("\n===== LOW STOCK REPORT =====");

        if (products.isEmpty()) {
            System.out.println("No low-stock products.");
            return;
        }

        products.forEach(System.out::println);
    }
}
