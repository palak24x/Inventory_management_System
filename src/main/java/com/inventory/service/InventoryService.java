package com.inventory.service;

import com.inventory.dao.ProductDAO;
import com.inventory.exception.InvalidQuantityException;
import com.inventory.exception.ProductNotFoundException;
import com.inventory.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryService {
    private final ProductDAO productDAO;

    public InventoryService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(Product product) throws SQLException {
        if (product.getQuantity() < 0 || product.getReorderThreshold() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        productDAO.addProduct(product);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    public Product findProduct(int id) throws SQLException, ProductNotFoundException {
        Product product = productDAO.getProductById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found.");
        }

        return product;
    }

    public void reduceStock(int id, int quantity)
            throws SQLException, ProductNotFoundException, InvalidQuantityException {

        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than 0.");
        }

        Product product = findProduct(id);

        if (product.getQuantity() < quantity) {
            throw new InvalidQuantityException("Not enough stock.");
        }

        productDAO.updateQuantity(id, product.getQuantity() - quantity);
    }

    public List<Product> getLowStockProducts() throws SQLException {
        return productDAO.getAllProducts()
                .stream()
                .filter(Product::isReorderNeeded)
                .collect(Collectors.toList());
    }
}
