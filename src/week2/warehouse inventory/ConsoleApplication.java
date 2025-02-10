package week2.inventoryManagementSystem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private static final InventoryManager inventoryManager = new InventoryManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        initializeProducts();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Warehouse Inventory Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Place Order");
            System.out.println("3. View Inventory");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    inventoryManager.printInventory();
                    break;
                case 4:
                    running = false;
                    inventoryManager.shutdown();
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private static void initializeProducts() {
        inventoryManager.addProduct(new Product("P001", "Laptop", 10, new Location(1, 2, 3)));
        inventoryManager.addProduct(new Product("P002", "Mouse", 15, new Location(1, 1, 1)));
        inventoryManager.addProduct(new Product("P003", "Keyboard", 20, new Location(2, 1, 2)));
        inventoryManager.addProduct(new Product("P004", "Monitor", 8, new Location(3, 3, 1)));
        inventoryManager.addProduct(new Product("P005", "Printer", 5, new Location(4, 2, 2)));
        System.out.println("Initial products added to inventory.");
    }

    private static void addProduct() {
        System.out.print("Enter product ID: ");
        String productID = scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter aisle number: ");
        int aisle = scanner.nextInt();

        System.out.print("Enter shelf number: ");
        int shelf = scanner.nextInt();

        System.out.print("Enter bin number: ");
        int bin = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Location location = new Location(aisle, shelf, bin);
        Product product = new Product(productID, name, quantity, location);
        inventoryManager.addProduct(product);
    }

    //    private static void placeOrder() {
//        System.out.print("Enter order ID: ");
//        String orderID = scanner.nextLine();
//        System.out.print("Enter product IDs (comma-separated): ");
//        String[] productIDs = scanner.nextLine().split(",");
//        System.out.print("Enter priority (STANDARD/EXPEDITED): ");
//        String priorityStr = scanner.nextLine().toUpperCase();
//        Order.Priority priority = Order.Priority.valueOf(priorityStr);
//
//        List<String> productIDList = Arrays.asList(productIDs);
//        Order order = new Order(orderID, productIDList, priority);
//        inventoryManager.processOrder(order);
//    }
    private static void placeOrder() {
        System.out.println("Available products:");
        inventoryManager.printInventory();

        System.out.print("Enter order ID: ");
        String orderID = scanner.nextLine();
        List<String> productIDList = new ArrayList<>();

        while (true) {
            System.out.print("Enter product ID to add to order (or type 'done' to finish): ");
            String productID = scanner.nextLine();
            if (productID.equalsIgnoreCase("done")) {
                break;
            }
            productIDList.add(productID);
        }

        if (productIDList.isEmpty()) {
            System.out.println("No products selected. Order cancelled.");
            return;
        }

        System.out.print("Enter priority (STANDARD/EXPEDITED): ");
        String priorityStr = scanner.nextLine().toUpperCase();

        try {
            Order.Priority priority = Order.Priority.valueOf(priorityStr);
            Order order = new Order(orderID, productIDList, priority);
            inventoryManager.processOrder(order);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority level. Order cancelled.");
        }
    }


}