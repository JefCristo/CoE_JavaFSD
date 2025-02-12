package week2.inventoryManagementSystem;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        // Initialize inventory
        inventoryManager.addProduct(new Product("P1", "Laptop", 10, new Location(1, 2, 3)));
        inventoryManager.addProduct(new Product("P2", "Mouse", 50, new Location(2, 3, 4)));
        inventoryManager.addProduct(new Product("P3", "Keyboard", 30, new Location(3, 4, 5)));

        // Simulate orders
        List<String> order1Products = Arrays.asList("P1", "P2");
        List<String> order2Products = Arrays.asList("P2", "P3");
        List<String> order3Products = Arrays.asList("P1", "P3");

        inventoryManager.processOrder(new Order("O1", order1Products, Order.Priority.STANDARD));
        inventoryManager.processOrder(new Order("O2", order2Products, Order.Priority.EXPEDITED));
        inventoryManager.processOrder(new Order("O3", order3Products, Order.Priority.STANDARD));

        // Shutdown inventory manager
        inventoryManager.shutdown();

        // Print final inventory state
        inventoryManager.printInventory();
    }
}