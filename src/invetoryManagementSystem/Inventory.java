/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invetoryManagementSystem;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Inventory class holds database of products
 *
 * @author Giri Rakesh
 */
public class Inventory implements InventoryManagementSystem {

    private ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();

    /**
     * no-argument constructor
     */
    public Inventory() {
    }

    /**
     * Constructor that initializes the hash map to store products
     *
     * @param products
     */
    public Inventory(ConcurrentHashMap<String, Product> products) {
        this.products = Objects.requireNonNull(products, "There are no products in the inventory.");
    }

    /**
     * This method gets the products available
     *
     * @return products
     */
    public ConcurrentHashMap<String, Product> getProducts() {
        return products;
    }

    @Override
    /**
     * This method is used to pick the required quantity from the available
     * quantity
     */
    public synchronized PickingResult pickProduct(String productId, int amountToPick) {
        Product item = products.get(productId);

        if (item != null) {
            if (amountToPick <= item.getProductStock()) {
                item.pickProduct(amountToPick);
                System.out.println(RestockingResult.transactionResult.Successful);
                return new PickingResult(products);
            } else {
                System.out.println(RestockingResult.transactionResult.InsufficientInventory);
                return new PickingResult(products);
            }
        } else {
            System.out.println(RestockingResult.transactionResult.NoSuchProduct);
            return new PickingResult(products);
        }
    }

    @Override
    /**
     * This method is used to add the given amount of quantity to the available
     * quantity
     */
    public synchronized RestockingResult restockProduct(String productId, int amountToRestock) {
        Product item = products.get(productId);
        if (item != null) {
            if (products.containsKey(productId)) {
                item.restockProduct(amountToRestock);
                System.out.println(RestockingResult.transactionResult.Successful);
                return new RestockingResult(products);
            } else {
                products.put(productId, item);
                System.out.println(RestockingResult.transactionResult.Successful);
                return new RestockingResult(products);
            }
        } else {
            System.out.println(RestockingResult.transactionResult.NoSuchProduct);
            return new RestockingResult(products);
        }
    }
}
