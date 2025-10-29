
import java.time.*;

public class Order {

    private int orderId;
    private static int countId = 401;
    private int customerId;
    private double totalPrice;
    private LocalDate orderDate;
    private String status;
    private List<Product> orderProducts;

    public Order(int orderId, int customerId, double totalPrice, LocalDate orderDate, String status) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.orderProducts = new LinkedList<>();
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order(int customerId, LocalDate orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderId = countId++;
        this.orderProducts = new LinkedList<>();
        this.status = "Pending"; // Default status
        this.totalPrice = 0;
    }

    public void cancelOrder() {
        this.status = "Canceled";
    }

    public void updateStatus(String status) {
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Id: ").append(orderId);
        sb.append("\nCustomer Id:").append(customerId);
        sb.append("\nTotal Price: ").append(totalPrice);
        sb.append("\nOrder Date: ").append(orderDate);
        sb.append("\nStatus: ").append(status);
        sb.append("\nProducts:");
        if (!orderProducts.empty()) {
            orderProducts.findFirst();
            while (true) {
                sb.append('\n');
                Product p = orderProducts.retrieve();
                sb.append(p.getProductId()).append("-").append(p.getName());
                orderProducts.findNext();
                if (orderProducts.last()) {
                    break;
                }
            }
        }
        return sb.toString();
    }
}
