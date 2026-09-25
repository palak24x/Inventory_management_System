# Smart Inventory & Reorder Alert System

A beginner-friendly Java console application for managing products, stock and low-stock alerts.

## Technologies
- Core Java
- OOP
- Collections / Streams
- Exception Handling
- JDBC
- MySQL
- Maven

## Main Features
1. Add perishable and non-perishable products
2. View products
3. Record sales
4. Automatically reduce stock after a sale
5. Show low-stock / reorder alerts
6. Store data in MySQL

## Setup

### 1. Create the database
Open MySQL Workbench and run:

`database/schema.sql`

### 2. Set your MySQL password
Open:

`src/main/java/com/inventory/dao/DBConnection.java`


In the terminal:

`mvn clean compile`

Then run:

`mvn exec:java`

If your Maven setup does not have the exec plugin, run the main class from VS Code:

`com.inventory.main.InventoryApp`

## Important
This version intentionally keeps the project simple. Supplier and Purchase Order model classes are included for the next development phase, but the first runnable version focuses on the core inventory flow.
