/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invetoryManagementSystem;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class holds the product specific information
 *
 * @author Giri Rakesh
 */
class Product {

    private String productID;
    private String productLocation;
    private AtomicInteger stockInHand;

    /**
     * No-argument constructor
     */
    public Product() {
    }

    /**
     * Constructor to initialize the below parameters
     *
     * @param productID
     * @param productLocation
     * @param productStock
     */
    public Product(String productID, String productLocation, int productStock) {
        this.productID = productID;
        this.productLocation = productLocation;
        this.stockInHand = new AtomicInteger(productStock);
    }

    /**
     * Getter method
     *
     * @return product stock in hand
     */
    public int getProductStock() {
        return stockInHand.get();
    }

    /**
     * Getter method
     *
     * @return product ID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Setter Method to set a product ID to a new product
     *
     * @param productID
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * Getter method to get the location of the product
     *
     * @return product location
     */
    public String getProductLocation() {
        return productLocation;
    }

    /**
     * Method to add required amount of quantity to stock in hand
     *
     * @param amountToAdd
     * @return stock in hand of the particular product
     */
    public int restockProduct(int amountToAdd) {
        return this.stockInHand.addAndGet(amountToAdd);
    }

    /**
     * Method to pick the required amount of quantity from the available
     * quantity
     *
     * @param amountToAdd
     * @return stock in hand of the particular product
     */
    public int pickProduct(int amountToAdd) {
        return this.stockInHand.addAndGet(-amountToAdd);
    }

}
