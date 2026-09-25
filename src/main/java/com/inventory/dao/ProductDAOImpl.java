package com.inventory.dao;

import com.inventory.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products " +
                "(name, price, quantity, reorder_threshold, product_type, expiry_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getReorderThreshold());
            ps.setString(5, product instanceof PerishableProduct
                    ? "PERISHABLE" : "NON_PERISHABLE");

            if (product.getExpiryDate() == null) {
                ps.setNull(6, Types.DATE);
            } else {
                ps.setDate(6, Date.valueOf(product.getExpiryDate()));
            }

            ps.executeUpdate();
        }
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(toProduct(rs));
            }
        }
        return products;
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return toProduct(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void updateQuantity(int id, int quantity) throws SQLException {
        String sql = "UPDATE products SET quantity = ? WHERE product_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    private Product toProduct(ResultSet rs) throws SQLException {
        int id = rs.getInt("product_id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity");
        int threshold = rs.getInt("reorder_threshold");
        String type = rs.getString("product_type");

        Date expiry = rs.getDate("expiry_date");
        LocalDate expiryDate = expiry == null ? null : expiry.toLocalDate();

        if ("PERISHABLE".equals(type)) {
            return new PerishableProduct(id, name, price, quantity,
                    threshold, expiryDate);
        }

        return new NonPerishableProduct(id, name, price, quantity, threshold);
    }
}
