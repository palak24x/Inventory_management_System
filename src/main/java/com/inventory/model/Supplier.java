package com.inventory.model;

public class Supplier {
    private int id;
    private String name;
    private String contactNumber;
    private String email;

    public Supplier(int id, String name, String contactNumber, String email) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
}
