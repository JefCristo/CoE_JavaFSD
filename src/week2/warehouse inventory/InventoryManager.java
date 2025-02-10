package week2.inventoryManagementSystem;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryManager {

    // concurrent hashmap to manage products, and their location
    // ensure thready safety

    private Map<String, Product> products;
    private PriorityQueue<Order> orderQueue;
    private ExecutorService executorService;

    public InventoryManager() {
        products = new ConcurrentHashMap<>();
        orderQueue = new PriorityQueue<>(Comparator.comparing(o -> o.getPriority() == Order.Priority.EXPEDITED ? 0 : 1));
//        orderQueue = new PriorityQueue<>((o1,o2) -> {
//            int p1 = o1.getPriority() == Order.Priority.EXPEDITED ? 0 : 1;
//            int p2 = o2.getPriority() == Order.Priority.EXPEDITED ? 0 : 1;
//            return Integer.compare(p1,p2);
//        });

        executorService = Executors.newFixedThreadPool(5);
    }

    /**
     * Implement 1. Searching, 2.Sorting , 3. Updating inventory items
     * use TreeMap and ArrayList
     */

    // add product: add the Product Object in the products hashMap
    // and sort them using TreeMap
    public synchronized void addProduct(Product product){
        products.put(product.getProductId(),product);
        System.out.println("ADDED PRODUCT: " + product);
    }


    /**
     *
     * @param order
     *  adds the orders in the orderQueue
     */
    public synchronized void processOrder(Order order){
        orderQueue.add(order);
        System.out.println("Added order to queue: "+ order);
        executorService.submit(() -> fulfillOrder(order));
    }


    /**
     * Fulfill Orders: Checks if product exists and has stock available
     * reduces 1 stock if available or error message if out of stock
     */
    private void fulfillOrder(Order order){
        for(String prdtId : order.getProductId()){
            Product product = products.get(prdtId);

            if(product != null && product.getQuantity() > 0){
                synchronized (product){
                    product.setQuantity(product.getQuantity() - 1);
                    System.out.println("Processed product: " + prdtId + " for order: " + order.getOrderId());
                }
            }else{
                System.out.println("Out of stock for product" + prdtId);
            }
        }
    }

    //shutdown
    public void shutdown(){
        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(60, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public void printInventory(){
        System.out.println("Current Inventory:");
        products.forEach((id, prdt) -> System.out.println(prdt));
    }

}
