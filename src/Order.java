
public class Order {

    private int orderId;
    private int customerId;
    private double totalPrice;
    private int orderDate;
    private String status;
    private List<Product> orderProducts;

    public Order(int customerId, int orderDate, int orderId) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderId = orderId;
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

    public int getCustomer() {
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

    public int getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(int orderDate) {
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

}
