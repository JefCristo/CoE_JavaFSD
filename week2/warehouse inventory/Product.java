package week2.inventoryManagementSystem;


public class Product {
    private  String productId;
    private  String name;
    private  int quantity;
    private Location location;

    public Product(String productId, String name, int quantity, Location location) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", location=" + location +
                '}';
    }
}
