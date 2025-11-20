package main;

import java.io.*;
import java.time.*;

import DAL.*;
import models.*;
import structures.*;

public class ECommerceSystem {

    private OrderOperations op;
    private CustomerOperations co;
    private ProductOperations po;

    private static final String PRODUCTS_FILE = "assets/prodcuts.csv";
    private static final String CUSTOMERS_FILE = "assets/customers.csv";
    private static final String ORDERS_FILE = "assets/orders.csv";
    private static final String REVIEWS_FILE = "assets/reviews.csv";

    public ECommerceSystem() {
        po = new ProductOperations();
        co = new CustomerOperations();
        op = new OrderOperations();
    }

    public void loadFiles() {
        String file = PRODUCTS_FILE;
        BufferedReader reader;
        String line;
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Product p = new Product(Integer.parseInt(row[0]), row[1], Double.parseDouble(row[2]),
                        Integer.parseInt(row[3]));
                po.addProduct(p);

            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        file = CUSTOMERS_FILE;

        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Customer c = new Customer(Integer.parseInt(row[0]), row[1], row[2]);
                co.insertCustomer(c);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        file = ORDERS_FILE;
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Order o = new Order(Integer.parseInt(row[0]), Integer.parseInt(row[1]), Double.parseDouble(row[3]),
                        LocalDate.parse(row[4]), row[5]);
                String[] productString = row[2].replace('"', ' ').trim().split(";");
                for (String index : productString) {
                    o.getOrderProducts().insert(searchProductId(Integer.parseInt(index)));
                }
                searchCustomerId(Integer.parseInt(row[1])).getOrderHistory().insert(o);
                op.addOrder(o);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        file = REVIEWS_FILE;
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Review r = new Review(Integer.parseInt(row[0]), Integer.parseInt(row[2]), Integer.parseInt(row[3]),
                        (row.length > 5 ? row[4] + "," + row[5] : row[4]));

                po.addReview(Integer.parseInt(row[1]), r);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addProduct(Product p) {
        try {
            if (po.addProduct(p)) {

                BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCTS_FILE, true));
                writer.newLine();
                writer.write(p.getProductId() + "," + p.getName() + "," + p.getPrice() + "," + p.getStock());
                writer.close();

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean removeProduct(int productId) {
        return po.removeProduct(productId);
    }

    public boolean updateProduct(int id, String name, double price, int stock) { // needs overwrite line in csv
        return po.updateProduct(id, name, price, stock);
    }

    public Product searchProductId(int productId) {
        return po.searchProductId(productId);
    }

    /*
     * public Product searchProductName(String name) {
     * Product p = null;
     * if (!allProducts.empty()) {
     * allProducts.findFirst();
     * 
     * while (true) {
     * if (allProducts.retrieve().getName().equalsIgnoreCase(name)) {
     * p = allProducts.retrieve();
     * }
     * if (allProducts.last()) {
     * break;
     * }
     * allProducts.findNext();
     * }
     * 
     * }
     * return p;
     * }
     */
    public List<Product> getProductsInRange(Double min, Double max) {
        return po.priceRange(min, max);

    }

    public void addReviewToProduct(Product p, Review r) {
        try {
            if (po.addReview(p.getProductId(), r)) {

                BufferedWriter writer = new BufferedWriter(new FileWriter(REVIEWS_FILE, true));
                writer.newLine();
                writer.write(
                        r.getReviewId() + "," + p.getProductId() + "," + r.getCustomerId() + "," + r.getRating() + ","
                                + "\"" + r.getComment() + "\"");
                writer.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public BinaryTree<Integer, Customer> productReviewers(int id) {
        BinaryTree<Integer, Customer> customers = new AVLTree<>();
        Product p = po.searchProductId(id);
        if (p != null) {
            List<Review> reviewsOfProduct = p.getReviews();
            if (!reviewsOfProduct.empty()) {
                reviewsOfProduct.findFirst();
                while (true) {
                    int customerId = reviewsOfProduct.retrieve().getCustomerId();
                    customers.insert(customerId, searchCustomerId(customerId));
                    if (reviewsOfProduct.last())
                        break;
                    reviewsOfProduct.findNext();
                }
            }

        }
        return customers;

    }
    /*
     * not needed in phase 2
     * public List<Product> trackOutOfStockProducts() {
     * List<Product> tosp = new LinkedList<>();
     * if (!allProducts.empty()) {
     * allProducts.findFirst();
     * 
     * while (true) {
     * if (allProducts.retrieve().getStock() == 0) {
     * tosp.insert(allProducts.retrieve());
     * }
     * if (allProducts.last()) {
     * break;
     * }
     * allProducts.findNext();
     * }
     * 
     * }
     * return tosp;
     * }
     */

    public boolean registerCustomer(Customer c) {
        // write to csv file
        try {
            if (co.insertCustomer(c)) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMERS_FILE, true));
                writer.newLine();
                writer.write(c.getCustomerId() + "," + c.getName() + "," + c.getEmail());
                writer.close();
                return true;
            } else
                return false;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public Customer searchCustomerName(String name) {
        return co.searchCustomerName(name);
    }

    public Customer searchCustomerId(int id) {
        return co.searchCustomerId(id);
    }

    public void addOrder(Order o) {
        // write to csv file
        try {
            if (op.addOrder(o)) {
                StringBuilder orderPID = new StringBuilder();
                orderPID.append('"');
                if (!o.getOrderProducts().empty()) {
                    o.getOrderProducts().findFirst();
                    while (true) {
                        if (orderPID.length() > 1) {
                            orderPID.append(';');
                        }
                        orderPID.append(o.getOrderProducts().retrieve().getProductId());
                        if (o.getOrderProducts().last()) {
                            break;
                        }
                        o.getOrderProducts().findNext();
                    }
                    orderPID.append('"');
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE, true));
                writer.newLine();
                writer.write(
                        o.getOrderId() + "," + o.getCustomerId() + "," + orderPID.toString() + "," + o.getTotalPrice()
                                + "," + o.getOrderDate() + "," + o.getStatus());
                writer.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Order placeOrder(Customer c, List<Product> products) {
        Order order = new Order(c.getCustomerId(), LocalDate.now());
        double totalPrice = 0;
        if (!products.empty()) {
            products.findFirst();
            while (true) {
                Product p = products.retrieve();
                order.getOrderProducts().insert(p);
                totalPrice += p.getPrice();
                p.setStock(p.getStock() - 1);
                if (products.last()) {
                    break;
                }
                products.findNext();
            }
        }
        order.setTotalPrice(totalPrice);
        c.getOrderHistory().insert(order);
        addOrder(order);
        return order;
    }

    public Order searchOrderId(int orderId) {
        return op.searchOrderById(orderId);
    }

    /*
     * public Review searchReviewId(int reviewId) {
     * Review r = null;
     * if (!allProducts.empty()) {
     * allProducts.findFirst();
     * while (true) {
     * Product p = allProducts.retrieve();
     * List<Review> reviews = p.getReviews();
     * 
     * if (!reviews.empty()) {
     * reviews.findFirst();
     * while (true) {
     * if (reviews.retrieve().getReviewId() == reviewId) {
     * r = reviews.retrieve();
     * }
     * if (reviews.last()) {
     * break;
     * }
     * reviews.findNext();
     * }
     * 
     * }
     * 
     * if (allProducts.last()) {
     * break;
     * }
     * allProducts.findNext();
     * }
     * }
     * return r;
     * }
     * 
     */
    public List<Product> getTopRatedProducts() {
        return po.getTopProducts();
        /*
         * List<Product> top = new LinkedList<>();
         * if (allProducts.empty()) {
         * return top;
         * }
         * Product first = null, second = null, third = null;
         * 
         * allProducts.findFirst();
         * while (true) {
         * Product p = allProducts.retrieve();
         * double rating = p.getAverageRating();
         * 
         * if (first == null || rating > first.getAverageRating()) {
         * third = second;
         * second = first;
         * first = p;
         * } else if (second == null || rating > second.getAverageRating()) {
         * third = second;
         * second = p;
         * } else if (third == null || rating > third.getAverageRating()) {
         * third = p;
         * }
         * 
         * if (allProducts.last()) {
         * break;
         * }
         * allProducts.findNext();
         * }
         * 
         * if (first != null) {
         * top.insert(first);
         * }
         * if (second != null) {
         * top.insert(second);
         * }
         * if (third != null) {
         * top.insert(third);
         * }
         * 
         * return top;
         */
    }

    public List<Order> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        return op.getOrdersBetweenDates(startDate, endDate);
    }

    /*
     * not needed in phase 2
     * public List<Review> getCustomerReviews(Customer c) {
     * List<Review> reviewsByC = new LinkedList<>();
     * if (!allProducts.empty()) {
     * allProducts.findFirst();
     * while (true) {
     * List<Review> reviews = allProducts.retrieve().getReviews();
     * if (!reviews.empty()) {
     * reviews.findFirst();
     * while (true) {
     * if (reviews.retrieve().getCustomerId() == c.getCustomerId()) {
     * reviewsByC.insert(reviews.retrieve());
     * }
     * 
     * reviews.findNext();
     * if (reviews.last()) {
     * break;
     * }
     * }
     * }
     * if (allProducts.last()) {
     * break;
     * }
     * allProducts.findNext();
     * 
     * }
     * }
     * return reviewsByC;
     * 
     * }
     */
    /*
     * not needed in phase 2
     * public List<Product> findCommonProducts(int customerId1, int customerId2) {
     * List<Product> result = new LinkedList<>();
     * if (!allProducts.empty()) {
     * allProducts.findFirst();
     * while (true) {
     * Product p = allProducts.retrieve();
     * boolean reviewedBy1 = false;
     * boolean reviewedBy2 = false;
     * List<Review> revs = p.getReviews();
     * if (!revs.empty()) {
     * revs.findFirst();
     * while (true) {
     * Review r = revs.retrieve();
     * if (r.getCustomerId() == customerId1) {
     * reviewedBy1 = true;
     * }
     * if (r.getCustomerId() == customerId2) {
     * reviewedBy2 = true;
     * }
     * if (revs.last()) {
     * break;
     * }
     * revs.findNext();
     * }
     * }
     * 
     * if (reviewedBy1 && reviewedBy2 && p.averageRating() > 4.0) {
     * result.insert(p);
     * }
     * if (allProducts.last()) {
     * break;
     * }
     * allProducts.findNext();
     * }
     * }
     * return result;
     * }
     */

    public void printAllProducts() {
        System.out.println("=== PRODUCTS ===");
        po.printSortedId(TraverseOrder.IN_ORDER);

    }

    public void printAllCustomers() {
        System.out.println("=== CUSTOMERS ===");
        co.printSortedName(TraverseOrder.IN_ORDER);
    }

    public void printAllOrders() {
        System.out.println("=== ORDERS ===");
        op.printSortedId(TraverseOrder.IN_ORDER);

    }

}
