package week2.inventoryManagementSystem;

import java.util.List;

class Order implements Comparable<Order> {
    private String orderId;
    private List<String> productId;
    private Priority priority;


    public  enum Priority{
        STANDARD, EXPEDITED
    }

    public Order(String orderId, List<String> productId, Priority priority) {
        this.orderId = orderId;
        this.productId = productId;
        this.priority = priority;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getProductId() {
        return productId;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public int compareTo( Order o) {
        return this.priority.compareTo(o.priority);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", productId=" + productId +
                ", priority=" + priority +
                '}';
    }
}

