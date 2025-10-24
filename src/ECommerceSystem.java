
import java.io.*; //for file loading, should also update files when adding items
import java.util.Scanner;
import java.time.*;

public class ECommerceSystem {

    private List<Product> allProducts;
    private List<Customer> allCustomers;
    private List<Order> allOrders;

    private static final String PRODUCTS_FILE = "assets/prodcuts.csv";
    private static final String CUSTOMERS_FILE = "assets/customers.csv";
    private static final String ORDERS_FILE = "assets/orders.csv";
    private static final String REVIEWS_FILE = "assets/reviews.csv";

    Scanner console = new Scanner(System.in);

    public ECommerceSystem() {
        this.allProducts = new LinkedList<>();
        this.allCustomers = new LinkedList<>();
        this.allOrders = new LinkedList<>();
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
                Product p = new Product(Integer.parseInt(row[0]), row[1], Double.parseDouble(row[2]), Integer.parseInt(row[3]));
                allProducts.insert(p);
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
                allCustomers.insert(c);
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
                Order o = new Order(Integer.parseInt(row[0]), Integer.parseInt(row[1]), Double.parseDouble(row[3]), LocalDate.parse(row[4]), row[5]);
                String[] productString = row[2].replace('"', ' ').trim().split(";");
                for (String index : productString) {
                    o.getOrderProducts().insert(searchProductId(Integer.parseInt(index)));
                }
                allOrders.insert(o);
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
                Review r = new Review(Integer.parseInt(row[0]), Integer.parseInt(row[2]), Integer.parseInt(row[3]), (row.length > 5 ? row[4] + "," + row[5] : row[4]));
                searchProductId(Integer.parseInt(row[1])).getReviews().insert(r);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addProduct(Product p) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("assets/prodcuts.csv", true));
            writer.newLine();
            writer.write(p.getProductId() + "," + p.getName() + "," + p.getPrice() + "," + p.getStock());
            writer.close();
            allProducts.insert(p);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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
        //write to csv file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("assets/customers.csv", true));
            writer.newLine();
            writer.write(c.getCustomerId() + "," + c.getName() + "," + c.getEmail());
            writer.close();
            allCustomers.insert(c);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Customer searchCustomerId(int customerId) {
        Customer c = null;
        if (!allCustomers.empty()) {
            allCustomers.findFirst();

            while (!allCustomers.last()) {
                if (allCustomers.retrieve().getCustomerId() == customerId) {
                    c = allCustomers.retrieve();
                }
                allCustomers.findNext();
            }
            if (allCustomers.retrieve().getCustomerId() == customerId) {
                c = allCustomers.retrieve();
            }
        }
        return c;

    }

    public void addOrder(Order o) {
        //write to csv file
        StringBuilder orderPID = new StringBuilder();
        orderPID.append('"');
        if (!o.getOrderProducts().empty()) {
            o.getOrderProducts().findFirst();
            while (!o.getOrderProducts().last()) {
                orderPID.append(o.getOrderProducts().retrieve().getProductId());
                orderPID.append(';');
            }
            orderPID.append(o.getOrderProducts().retrieve().getProductId());
            orderPID.append('"');
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("assets/orders.csv", true));
            writer.newLine();
            writer.write(o.getOrderId() + "," + o.getCustomerId() + "," + orderPID.toString() + "," + o.getTotalPrice() + "," + o.getOrderDate() + "," + o.getStatus());
            writer.close();
            allOrders.insert(o);
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
                order.getOrderProducts().insert(products.retrieve());
                totalPrice += products.retrieve().getPrice();
                products.retrieve().setStock(products.retrieve().getStock() - 1);
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
        List<Product> top = new LinkedList<>();
        if (allProducts.empty()) {
            return top;
        }
        Product first = null, second = null, third = null;

        allProducts.findFirst();
        while (true) {
            Product p = allProducts.retrieve();
            double rating = p.averageRating();

            if (first == null || rating > first.averageRating()) {
                third = second;
                second = first;
                first = p;
            } else if (second == null || rating > second.averageRating()) {
                third = second;
                second = p;
            } else if (third == null || rating > third.averageRating()) {
                third = p;
            }

            if (allProducts.last()) {
                break;
            }
            allProducts.findNext();
        }

        if (first != null) {
            top.insert(first);
        }
        if (second != null) {
            top.insert(second);
        }
        if (third != null) {
            top.insert(third);
        }

        return top;
    }

    public List<Order> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        //get orders between two dates and return list
        List<Order> result = new LinkedList<>();
        if (!allOrders.empty()) {
            Order o;
            allOrders.findFirst();
            while (true) {
                o = allOrders.retrieve();
                LocalDate date = o.getOrderDate();
                if (date.isAfter(startDate) && date.isBefore(endDate)) {
                    result.insert(o);
                }
                allOrders.findNext();
                if (allOrders.last()) {
                    break;
                }
            }

        }
        return result;
    }

    public List<Review> getCustomerReviews(Customer c) {
        List<Review> reviewsByC = new LinkedList<>();
        if (!allProducts.empty()) {
            allProducts.findFirst();
            while (true) {
                List<Review> reviews = allProducts.retrieve().getReviews();
                if (!reviews.empty()) {
                    reviews.findFirst();
                    while (true) {
                        if (reviews.retrieve().getCustomerId() == c.getCustomerId()) {
                            reviewsByC.insert(reviews.retrieve());
                        }

                        reviews.findNext();
                        if (reviews.last()) {
                            break;
                        }
                    }
                }
                allProducts.findNext();
                if (allProducts.last()) {
                    break;
                }
            }
        }
        return reviewsByC;

    }

    public List<Product> findCommonProducts(int customerId1, int customerId2) {
        //find common products reviewed by two customers with product's average rating above 4.0(4.1, 4.2...5) and return list
        List<Product> result = new LinkedList<>();
        if (!allProducts.empty()) {
            allProducts.findFirst();
            while (true) {
                Product p = allProducts.retrieve();
                boolean reviewedBy1 = false;
                boolean reviewedBy2 = false;
                List<Review> revs = p.getReviews();
                if (!revs.empty()) {
                    revs.findFirst();
                    while (true) {
                        Review r = revs.retrieve();
                        if (r.getCustomerId() == customerId1) {
                            reviewedBy1 = true;
                        }
                        if (r.getCustomerId() == customerId2) {
                            reviewedBy2 = true;
                        }
                        if (revs.last()) {
                            break;
                        }
                        revs.findNext();
                    }
                }

                if (reviewedBy1 && reviewedBy2 && p.averageRating() > 4.0) {
                    result.insert(p);
                }
                if (allProducts.last()) {
                    break;
                }
                allProducts.findNext();
            }
        }
        return result;
    }
    // add printing methods for lists

    public void printAllProducts() {
        System.out.println("=== PRODUCTS ===");
        if (allProducts.empty()) {
            System.out.println("No products found");
            return;
        }
        allProducts.findFirst();
        while (true) {
            Product p = allProducts.retrieve();
            System.out.println("Product ID: " + p.getProductId());
            System.out.println("Name: " + p.getName());
            System.out.println("Price: " + p.getPrice());
            System.out.println("Stock: " + p.getStock());
            System.out.println("Average Rating: " + p.averageRating());
            System.out.println("-------------------------");

            if (allProducts.last()) {
                break;
            }
            allProducts.findNext();
        }

    }

    public void printAllCustomers() {
        System.out.println("=== CUSTOMERS ===");
        if (allCustomers.empty()) {
            System.out.println("No customers found");
            return;
        }

        allCustomers.findFirst();
        while (true) {
            Customer c = allCustomers.retrieve();
            System.out.println("Customer ID: " + c.getCustomerId());
            System.out.println("Name: " + c.getName());
            System.out.println("Email: " + c.getEmail());

            if (allCustomers.last()) {
                break;
            }
            allCustomers.findNext();
        }

    }

    public void printAllOrders() {
        System.out.println("=== ORDERS ===");
        if (allOrders.empty()) {
            System.out.println("No orders found");
            return;
        }

        allOrders.findFirst();
        while (true) {
            Order o = allOrders.retrieve();
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Customer ID: " + o.getCustomerId());
            System.out.println("Order Date: " + o.getOrderDate());
            System.out.println("Total Price: " + o.getTotalPrice());
            System.out.println("Total Price: " + o.getStatus());

            // to get the number of items in the order instead of inserting all items
            int countItems = 0;
            List<Product> p = o.getOrderProducts();
            if (!p.empty()) {
                p.findFirst();
                countItems = 1;
                while (!p.last()) {
                    countItems++;
                    p.findNext();
                }
            }
            System.out.println("Number of Items: " + countItems);
            System.out.println("-------------------------");

            if (allOrders.last()) {
                break;
            }
            allOrders.findNext();
        }

    }

}
