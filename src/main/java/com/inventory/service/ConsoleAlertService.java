package com.inventory.service;

import com.inventory.model.Product;

public class ConsoleAlertService implements AlertService {

    @Override
    public void sendAlert(Product product) {
        System.out.println("LOW STOCK / REORDER ALERT: " + product.getName());
    }
}
