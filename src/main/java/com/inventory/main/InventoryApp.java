package com.inventory.main;

import com.inventory.dao.ProductDAOImpl;
import com.inventory.exception.InvalidQuantityException;
import com.inventory.exception.ProductNotFoundException;
import com.inventory.model.NonPerishableProduct;
import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;
import com.inventory.service.InventoryService;
import com.inventory.service.ReportService;
import com.inventory.service.SalesService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class InventoryApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        InventoryService inventoryService =
                new InventoryService(new ProductDAOImpl());

        SalesService salesService = new SalesService(inventoryService);
        ReportService reportService = new ReportService(inventoryService);

        while (true) {
            showMenu();

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> addProduct(inventoryService);
                    case 2 -> viewProducts(inventoryService);
                    case 3 -> recordSale(salesService);
                    case 4 -> reportService.showLowStockReport();
                    case 5 -> {
                        System.out.println("Thank you. Program closed.");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (ProductNotFoundException | InvalidQuantityException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Record Sale");
        System.out.println("4. View Low Stock Alerts");
        System.out.println("5. Exit");
    }

    private static void addProduct(InventoryService service) throws SQLException {

        String name = readText("Product name: ");
        double price = readDouble("Price: ");
        int quantity = readInt("Quantity: ");
        int threshold = readInt("Reorder threshold: ");

        System.out.println("1. Perishable");
        System.out.println("2. Non-Perishable");

        int type = readInt("Product type: ");

        Product product;

        if (type == 1) {
            String expiry = readText("Expiry date (YYYY-MM-DD): ");

            product = new PerishableProduct(
                    0, name, price, quantity, threshold,
                    LocalDate.parse(expiry)
            );
        } else if (type == 2) {
            product = new NonPerishableProduct(
                    0, name, price, quantity, threshold
            );
        } else {
            throw new IllegalArgumentException("Invalid product type.");
        }

        service.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void viewProducts(InventoryService service) throws SQLException {

        List<Product> products = service.getAllProducts();

        System.out.println("\n===== PRODUCTS =====");

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        products.forEach(System.out::println);
    }

    private static void recordSale(SalesService service)
            throws SQLException, ProductNotFoundException, InvalidQuantityException {

        int productId = readInt("Product ID: ");
        int quantity = readInt("Quantity sold: ");

        service.recordSale(productId, quantity);

        System.out.println("Sale recorded and stock updated.");
    }

    private static int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    private static double readDouble(String message) {
        System.out.print(message);
        return Double.parseDouble(scanner.nextLine());
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
