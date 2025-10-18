
import java.io.*;
public class ECommerceSystem {

    private List<Product> allProducts;
    private List<Customer> allCustomers;
    private List<Order> allOrders;

    public ECommerceSystem() {
        this.allProducts = new LinkedList<>();
        this.allCustomers = new LinkedList<>();
        this.allOrders = new LinkedList<>();
    }

    public void loadFiles(String productsFile, String customersFile, String ordersFile) {
        
        //loading files
    }

    public void addProduct(Product p) {
        allProducts.insert(p);
    }

    public boolean removeProduct(int productId) {
        // Remove product by ID or set stock to 0 if found
        /*Product product = searchProductId(productId);
        if (product != null) {
            product.setStock(0);
            return true;
        }*/
        return false;
    }

    public boolean updateProduct(Product p) {
        //update product details (name, price, stock)
        return false;
    }

    public Product searchProductId(int productId) {
        //search product by ID then return product
        return null;
    }

    public Product searchProductName(String name) {
        //search product by name then return product
        return null;
    }

    public List<Product> trackOutOfStockProducts() {
        //track out of stock products then return list
        return null;
    }

    public void registerCustomer(Customer c) {
        allCustomers.insert(c);
    }

    public Order placeOrder(Customer c, List<Product> products) {
        //create new order
        return null;
    }

    public Order searchOrderId(int orderId) {
        //search orders by id then return order
        return null;
    }

    public List<Order> getOrderHistory(Customer c) {
        //search orders by customer
        return null;
    }

    public List<Product> getTopratedProducts(){
        //get 3 top rated products by average rating
        return null;
    }

    public List<Order> getOrdersBetweenDates(int startDate, int endDate){
        //get orders between two dates
        return null;
    }

    public List<Review> getCustomerReviews(Customer c){
        //get all reviews on every product ordered by customer
        return null;
    }

    public List<Product> findCommonProducts(int customerId1, int customerId2){
        //find common products reviewed by two customers with products' average rating above 4.0
        return null;
    }







}
