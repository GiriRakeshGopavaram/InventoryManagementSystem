/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invetoryManagementSystem;

import java.util.concurrent.ConcurrentHashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Giri Rakesh
 */
public class InventoryTest implements Runnable {

    Inventory database;
    Thread dbThread;
    ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();

    public InventoryTest() {

    }

    @Before
    public void setUp() {
        Product console = new Product("abc1", "Unit1", 10);
        Product mouse = new Product("abc2", "Unit2", 20);
        Product laptop = new Product("abc3", "Unit3", 30);

        products.put("abc1", console);
        products.put("abc2", mouse);
        products.put("abc3", laptop);
        database = new Inventory(products);
        dbThread = new Thread(this);
        dbThread.start();
    }

    @Test
    public void testPicking() {
        PickingResult result = database.pickProduct("abc1", 10);
        products = database.getProducts();
        Product product = products.get("abc1");
        assertTrue(product.getProductLocation().equals("Unit1") && product.getProductStock() == 0);
    }

    @Test
    public void testRestocking() {
        RestockingResult result = database.restockProduct("abc1", 10);
        products = database.getProducts();
        Product product = products.get("abc1");
        assertTrue(product.getProductLocation().equals("Unit1") && product.getProductStock() == 20);
    }

    @Test
    public void testConcurrency() throws InterruptedException {

        PickingResultTest pThread1 = new PickingResultTest(database, "abc1", 10);
        PickingResultTest pThread2 = new PickingResultTest(database, "abc1", 10);
        RestockingResultTest rThread = new RestockingResultTest(database, "abc2", 10);
        Thread t1 = new Thread(pThread1);
        Thread t2 = new Thread(pThread2);
        Thread t3 = new Thread(rThread);
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    @Override
    public void run() {
    }
}
