package com.inventory.dao;

import com.inventory.model.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    void addProduct(Product product) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    Product getProductById(int id) throws SQLException;
    void updateQuantity(int id, int quantity) throws SQLException;
}
