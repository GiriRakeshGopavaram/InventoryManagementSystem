/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invetoryManagementSystem;

import java.util.concurrent.ConcurrentHashMap;

/**
 * This class provides the result of the expected transaction
 *
 * @author Giri Rakesh
 */
class RestockingResult extends Inventory {

    /**
     * Enum that holds the result of the transaction
     */
    public enum transactionResult {
        Successful,
        NoSuchProduct,
        InsufficientInventory;
    }

    /**
     * no argument constructor
     */
    public RestockingResult() {
    }

    /**
     * Calling super class constructor
     *
     * @param products
     */
    public RestockingResult(ConcurrentHashMap<String, Product> products) {
        super(products);
    }
}
