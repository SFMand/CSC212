import java.time.*;
public class Order {

    private int orderId;
    private int countId = 401;
    private int customerId;
    private double totalPrice;
    private LocalDate orderDate;
    private String status;
    private List<Product> orderProducts;

    public Order(int orderId,int customerId, LocalDate orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.orderProducts = new LinkedList<>();
        this.status = "Pending"; // Default status
        this.totalPrice = 0;
    }
    public Order(int customerId, LocalDate orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderId = countId++;
        this.orderProducts = new LinkedList<>();
        this.status = "Pending"; // Default status
        this.totalPrice = 0;
    }

    public void cancelOrder(){
        this.status = "Canceled";
    }

    public void updateStatus(String status){
        this.status = status;
    }
    //  setter/getters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomer(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    //print method needed
    public void printOrder() {
    System.out.println("Order ID: " + orderId);
    System.out.println("Customer ID: " + customerId);
    System.out.println("Order Date: " + orderDate);
    System.out.println("Status: " + status);
    System.out.println("Total Price: " + totalPrice);

    if (orderProducts.empty()) {
        System.out.println("No products in this order.");
    } else {
        System.out.println("Ordered Products:");
        orderProducts.findFirst();
        while (true) {
            orderProducts.retrieve().printProduct();
            if (orderProducts.last()) break;
            orderProducts.findNext();
        }
    }
}


}