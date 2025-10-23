
public class Customer {

    private int customerId;
    private int countId = 231;
    private String name;
    private String email;
    private List<Order> orders;

    public Customer(String name, String email) {
        this.customerId = countId++;
        this.name = name;
        this.email = email;
        orders = new LinkedList<>();
    }

    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        orders = new LinkedList<>();
    }

    public List<Order> getOrderHistory() {
        return orders;
    }

    // setter/getters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    //print method needed

}
