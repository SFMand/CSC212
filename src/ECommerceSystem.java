
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
        String file = productsFile;
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

        file = customersFile;

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

        file = ordersFile;
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

        file = reviewsFile;
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Review r = new Review(Integer.parseInt(row[0]), Integer.parseInt(row[2]), Integer.parseInt(row[3]), row[4]);
                searchProductId(Integer.parseInt(row[1])).getReviews().insert(r);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addProduct(Product p) {
        //write to csv file
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
        //write to csv file
        allCustomers.insert(c);
    }

    public Customer searchCustomerId(int customerId) {
        Customer c = null;
        if (!allCustomers.empty()) {
            allCustomers.findFirst();

            while (!allCustomers.last()) {
                if (allCustomers.retrieve().getCustomerId() == customerId) {
                    c = allCustomers.retrieve();
                }
                allProducts.findNext();
            }
            if (allCustomers.retrieve().getCustomerId() == customerId) {
                c = allCustomers.retrieve();
            }
        }
        return c;

    }

    public void addOrder(Order o) {
        //write to csv file
        allOrders.insert(o);
    }

    public Order placeOrder(Customer c, List<Product> products) {
        Order order = new Order(c.getCustomerId(), LocalDate.now());
        double totalPrice = 0;
        if (!products.empty()) {
            products.findFirst();
            while (!products.last()) {
                order.getOrderProducts().insert(products.retrieve());
                totalPrice += products.retrieve().getPrice();
                products.retrieve().setStock(products.retrieve().getStock() - 1);
                products.findNext();
            }
            order.getOrderProducts().insert(products.retrieve());
            totalPrice += products.retrieve().getPrice();
            products.retrieve().setStock(products.retrieve().getStock() - 1);
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
