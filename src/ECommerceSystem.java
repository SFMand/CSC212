
import java.io.*; //for file loading, should also update files when adding items

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
        /* Search product by ID then set stock to 0 if found
        Product product = searchProductId(productId);
        if (product != null) {
            product.setStock(0);
            return true;
        }
        OR move current thru list then allProducts.remove()
         */
        return false;
    }

    public boolean updateProduct(Product p) {
        //update product details (name, price, stock), if not found return false
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
        //search out of stock products then return list
        return null;
    }

    public void registerCustomer(Customer c) {
        allCustomers.insert(c);
    }

    public Order placeOrder(Customer c, List<Product> products) {
        //create new order, list of products should up to customer choice
        return null;
    }

    public Order searchOrderId(int orderId) {
        //search orders by id then return order
        return null;
    }

    public List<Order> getOrderHistory(Customer c) {
        //search orders by customer then return list
        return null;
    }

    public List<Product> getTopRatedProducts() {
        //get 3 top rated products by average rating and return list
        return null;
    }

    public List<Order> getOrdersBetweenDates(int startDate, int endDate) {
        //get orders between two dates and return list
        return null;
    }

    public List<Review> getCustomerReviews(Customer c) {
        //get all reviews on every product ordered by customer and return list
        return null;
    }

    public List<Product> findCommonProducts(int customerId1, int customerId2) {
        //find common products reviewed by two customers with product's average rating above 4.0(4.1, 4.2...5) and return list
        return null;
    }
    // add printing methods for lists

}
