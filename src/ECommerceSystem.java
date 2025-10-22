
import java.io.*; //for file loading, should also update files when adding items
import java.util.Scanner;
import java.time.*;

public class ECommerceSystem {

    private List<Product> allProducts;
    private List<Customer> allCustomers;
    private List<Order> allOrders;

    Scanner console = new Scanner(System.in);

    public ECommerceSystem() {
        this.allProducts = new LinkedList<>();
        this.allCustomers = new LinkedList<>();
        this.allOrders = new LinkedList<>();
    }

    public void loadFiles(String productsFile, String customersFile, String ordersFile, String reviewsFile) {

        //loading files
    }

    public void addProduct(Product p) {
        allProducts.insert(p);
    }

    public boolean removeProduct(int productId) {
        Product p = searchProductId(productId);
        if (p != null) {
            p.setStock(0);
            return true;
        }
        return false;
    }

    public boolean updateProduct(Product p) {
        if (searchProductId(p.getProductId()) != null) {
            System.out.println("Enter new name:");
            String name = console.next();
            p.setName(name);
            System.out.println("Enter new price:");
            double price = console.nextDouble();
            p.setPrice(price);
            System.out.println("Enter new stock:");
            int stock = console.nextInt();
            p.setStock(stock);

            return true;
        }
        return false;
    }

    public Product searchProductId(int productId) {
        Product p = null;
        if (!allProducts.empty()) {
            allProducts.findFirst();

            while (!allProducts.last()) {
                if (allProducts.retrieve().getProductId() == productId) {
                    p = allProducts.retrieve();
                }
                allProducts.findNext();
            }
            if (allProducts.retrieve().getProductId() == productId) {
                p = allProducts.retrieve();
            }
        }
        return p;
    }

    public Product searchProductName(String name) {
        Product p = null;
        if (!allProducts.empty()) {
            allProducts.findFirst();

            while (!allProducts.last()) {
                if (allProducts.retrieve().getName().equalsIgnoreCase(name)) {
                    p = allProducts.retrieve();
                }
                allProducts.findNext();
            }
            if (allProducts.retrieve().getName().equalsIgnoreCase(name)) {
                p = allProducts.retrieve();
            }
        }
        return p;
    }

    public List<Product> trackOutOfStockProducts() {
        List<Product> tosp = new LinkedList<>();
        if (!allProducts.empty()) {
            allProducts.findFirst();

            while (!allProducts.last()) {
                if (allProducts.retrieve().getStock() == 0) {
                    tosp.insert(allProducts.retrieve());
                }
                allProducts.findNext();
            }
            if (allProducts.retrieve().getStock() == 0) {
                tosp.insert(allProducts.retrieve());

            }
        }
        return tosp;
    }

    public void registerCustomer(Customer c) {
        allCustomers.insert(c);
    }

    public Order placeOrder(Customer c, List<Product> products) {
        Order order = new Order(c.getCustomerId(), LocalDate.now());
        double totalPrice = 0;
        if (!products.empty()) {
            products.findFirst();
            while (!products.last()) {
                order.getOrderProducts().insert(products.retrieve());
                totalPrice += products.retrieve().getPrice();
                products.findNext();
            }
            order.getOrderProducts().insert(products.retrieve());
            totalPrice += products.retrieve().getPrice();
        }
        order.setTotalPrice(totalPrice);
        c.getOrderHistory().insert(order);
        allOrders.insert(order);
        return order;
    }

    public Order searchOrderId(int orderId) {
        Order o = null;
        if (!allOrders.empty()) {
            allOrders.findFirst();
            while (!allOrders.last()) {
                if (allOrders.retrieve().getOrderId() == orderId) {
                    o = allOrders.retrieve();
                }
                allOrders.findNext();
            }
            if (allOrders.retrieve().getOrderId() == orderId) {
                o = allOrders.retrieve();
            }
        }
        return o;
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
        List<Review> reviewsByC = new LinkedList<>();
        List<Order> ordersByC = c.getOrderHistory();

        if (!ordersByC.empty()) {
            ordersByC.findFirst();
            while (!ordersByC.last()) {
                List<Product> productsInOrder = ordersByC.retrieve().getOrderProducts();
                addReviewsToList(productsInOrder, reviewsByC);
                ordersByC.findNext();
            }
            List<Product> productsInOrder = ordersByC.retrieve().getOrderProducts();
            addReviewsToList(productsInOrder, reviewsByC);
        }

        return reviewsByC;
    }

    public void addReviewsToList(List<Product> products, List<Review> target) {
        if (!products.empty()) {
            products.findFirst();
            while (!products.last()) {
                List<Review> reviewsOfProduct = products.retrieve().getReviews();
                if (!reviewsOfProduct.empty()) {
                    reviewsOfProduct.findFirst();
                    while (!reviewsOfProduct.last()) {
                        target.insert(reviewsOfProduct.retrieve());
                        reviewsOfProduct.findNext();
                    }
                    target.insert(reviewsOfProduct.retrieve());

                }
                products.findNext();
            }
            List<Review> reviewsOfProduct = products.retrieve().getReviews();
            if (!reviewsOfProduct.empty()) {
                reviewsOfProduct.findFirst();
                while (!reviewsOfProduct.last()) {
                    target.insert(reviewsOfProduct.retrieve());
                    reviewsOfProduct.findNext();
                }
                target.insert(reviewsOfProduct.retrieve());
            }
        }
    }

    public List<Product> findCommonProducts(int customerId1, int customerId2) {
        //find common products reviewed by two customers with product's average rating above 4.0(4.1, 4.2...5) and return list
        return null;
    }
    // add printing methods for lists

}
