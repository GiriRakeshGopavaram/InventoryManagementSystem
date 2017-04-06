/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invetoryManagementSystem;

import org.junit.Test;

/**
 *
 * @author Giri Rakesh
 */
public class PickingResultTest implements Runnable {

    Inventory database;
    String productId;
    int amountToPick;

    public PickingResultTest(Inventory database, String productId, int amountToPick) {
        this.database = database;
        this.productId = productId;
        this.amountToPick = amountToPick;
    }

    @Test
    public void testPickStock() throws InterruptedException {
        PickingResult result = null;
        for (int i = 0; i < amountToPick; i++) {
            result = database.pickProduct(productId, amountToPick);
        }
        Thread.sleep(3000);

    }

    @Override
    public void run() {
        try {
            testPickStock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
