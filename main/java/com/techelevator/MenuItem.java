package com.techelevator;

public abstract class MenuItem {

    private String productName;
    private String productCode;
    private String productDescription;
    private String upsaleMessage;
    private double productPrice;
    private int quantity;

    //abstract class signifies that other classes that "are a" menu item should inherit from it

    //MenuItem constructor
    public MenuItem(String productName, String productCode, String productDescription,
                    String upsaleMessage, int quantity, double productPrice) {
        this.productName = productName;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.upsaleMessage = upsaleMessage;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }


    //getters and setters for MenuItem
    public String getProductName() {
        return productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getUpsaleMessage() {
        return upsaleMessage;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}