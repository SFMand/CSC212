package models;
import structures.*;

public class Customer {

    private int customerId;
    private static int countId = 231;
    private String name;
    private String email;
    private BinaryTree<Integer, Order> orders;

    public Customer(String name, String email) {
        this.customerId = countId++;
        this.name = name;
        this.email = email;
        orders = new AVLTree<>();
    }

    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        orders = new AVLTree<>();
    }

    public BinaryTree<Integer, Order> getOrderHistory() {
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

    public void setOrders(BinaryTree<Integer, Order> orders) {
        this.orders = orders;
    }
    //print method needed

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Id:").append(customerId);
        sb.append("\nName: ").append(name);
        sb.append("\nEmail:").append(email);
        return sb.toString();
    }
    
}
