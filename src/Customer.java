public class Customer {
    private int customerId;
    private String name;
    private String email;
    private List<Order> orders;

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
   public void printCustomer() {
    System.out.println("Customer ID: " + customerId);
    System.out.println("Name: " + name);
    System.out.println("Email: " + email);

    if (orders.empty()) {
        System.out.println("No orders yet.");
    } else {
        System.out.println("Order History:");
        orders.findFirst();
        while (true) {
            orders.retrieve().printOrder();
            if (orders.last()) break;
            orders.findNext();
        }
    }
}


    
}