
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AdminFrame extends JFrame {

    private JTextArea consoleOutputArea;
    private ECommerceSystem eCSystem;

    public AdminFrame() {
        setTitle("Admin View");
        setSize(1100, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        eCSystem = new ECommerceSystem();

        eCSystem.loadFiles();
        System.out.println("Files loaded");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1, 5, 5));

        JButton listProducts = new JButton("List All Products");
        JButton listCustomers = new JButton("List All Customers");
        JButton addProduct = new JButton("Add Product");
        JButton findProduct = new JButton("Find Product by ID");
        JButton findProductName = new JButton("Find Product by Name");
        JButton removeProduct = new JButton("Remove Product by ID");
        JButton updateProduct = new JButton("Update Product Info By ID");
        JButton TOSProducts = new JButton("List All Out Of Stock Products");
        JButton registerCustomer = new JButton("Register Customer");
        JButton findCustomer = new JButton("Find Customer by ID");
        JButton placeOrder = new JButton("Place Order for Customer");
        JButton findOrder = new JButton("Find Order by ID");
        JButton topRated = new JButton("List Top Rated Products");
        JButton ordersBetweenDates = new JButton("List Orders Between Two Dates");
        JButton customerReviews = new JButton("List a Customer's Reviews");
        JButton commonProducts = new JButton("List Common Products Between Two Customers");
        //missing buttons
        //functions including review are bugged

        buttonPanel.add(listProducts);
        buttonPanel.add(listCustomers);
        buttonPanel.add(addProduct);
        buttonPanel.add(findProduct);
        buttonPanel.add(findProductName);
        buttonPanel.add(removeProduct);
        buttonPanel.add(updateProduct);
        buttonPanel.add(TOSProducts);
        buttonPanel.add(registerCustomer);
        buttonPanel.add(findCustomer);
        buttonPanel.add(placeOrder); //missing implementation
        buttonPanel.add(findOrder);
        buttonPanel.add(topRated);
        buttonPanel.add(ordersBetweenDates); //missing implementation
        buttonPanel.add(customerReviews);
        buttonPanel.add(commonProducts);

        consoleOutputArea = new JTextArea();
        consoleOutputArea.setEditable(false);
        consoleOutputArea.setFont(new Font("Consolas", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(consoleOutputArea);

        setLayout(new BorderLayout(10, 10));
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        redirectSystemOutput();

        listProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("--- Listing All Products ---");
                eCSystem.printAllProducts();

            }
        });

        listCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("--- Listing All Customers ---");
                eCSystem.printAllCustomers();
            }

        });

        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String name = JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Name:", "Add Product", JOptionPane.QUESTION_MESSAGE);
                    double price = Double.parseDouble(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Price:", "Add Product", JOptionPane.QUESTION_MESSAGE));
                    int stock = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Stock:", "Add Product", JOptionPane.QUESTION_MESSAGE));
                    if (name != null)
                    eCSystem.addProduct(new Product(name, price, stock));

                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }

        });

        findProductName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Name:", "Find Product", JOptionPane.QUESTION_MESSAGE);
                System.out.println("--- Searching for Product w/Name: " + name + " ---");
                Product p = eCSystem.searchProductName(name);
                if (p != null) {
                    System.out.println(p);
                } else {
                    System.out.println("Product not found.");
                }

            }

        });

        removeProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Id: ", "Remove Product", JOptionPane.QUESTION_MESSAGE));
                    if (eCSystem.removeProduct(id)) {
                        System.out.println("Product Removed Succesfuly");
                    } else {
                        System.out.println("Product Not Found");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input:" + ex.getMessage());
                }
            }

        });

        findProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product ID:", "Find Product", JOptionPane.QUESTION_MESSAGE));
                    System.out.println("--- Searching for Product ID: " + id + " ---");
                    Product p = eCSystem.searchProductId(id);
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        System.out.println("Product not found.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }

        });

        updateProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Id to be updated:", "Update Product", JOptionPane.QUESTION_MESSAGE));
                    String name = JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Name:", "Update Product", JOptionPane.QUESTION_MESSAGE);
                    double price = Double.parseDouble(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Price:", "Update Product", JOptionPane.QUESTION_MESSAGE));
                    int stock = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Stock:", "Update Product", JOptionPane.QUESTION_MESSAGE));
                    if (name != null)
                    eCSystem.updateProduct(id, name, price, stock);
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }

        });

        TOSProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("--- Listing All Out Of Stock Products ---");
                eCSystem.trackOutOfStockProducts().print();
            }

        });

        registerCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer Name:", "Register Customer", JOptionPane.QUESTION_MESSAGE);
                if (name != null) {
                    String email = JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer Email:", "Register Customer", JOptionPane.QUESTION_MESSAGE);
                    if (email != null) {
                        eCSystem.registerCustomer(new Customer(name, email));

                    }
                }

            }

        });

        findCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer ID:", "Find Customer", JOptionPane.QUESTION_MESSAGE));
                    System.out.println("--- Searching for Customer ID: " + id + " ---");
                    Customer c = eCSystem.searchCustomerId(id);
                    if (c != null) {
                        System.out.println(c);
                    } else {
                        System.out.println("Customer not found.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }

            }

        });

        placeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //missing implementation

            }
        });

        findOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Order ID:", "Find Order", JOptionPane.QUESTION_MESSAGE));
                    System.out.println("--- Searching for Order ID: " + id + " ---");
                    Order o = eCSystem.searchOrderId(id);
                    if (o != null) {
                        System.out.println(o);
                    } else {
                        System.out.println("Order not found.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }

            }

        });

        topRated.addActionListener(new ActionListener() { //bugged
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("--- Listing Top Rated Products ---");
                eCSystem.getTopRatedProducts().print();
            }

        });

        ordersBetweenDates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //missing implementation
            }
        });

        customerReviews.addActionListener(new ActionListener() { //bugged
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer ID:", "Find Customer", JOptionPane.QUESTION_MESSAGE));
                    Customer c = eCSystem.searchCustomerId(id);

                    if (c != null) {
                        eCSystem.getCustomerReviews(c).print();
                    } else {
                        System.out.println("Customer not Found.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input" + ex.getMessage());
                }

            }

        });

        commonProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id1 = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer 1 ID:", "Find Customers", JOptionPane.QUESTION_MESSAGE));
                    if (eCSystem.searchCustomerId(id1) != null) {
                        int id2 = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer 2 ID:", "Find Customers", JOptionPane.QUESTION_MESSAGE));
                        if (eCSystem.searchCustomerId(id2) != null) {
                            eCSystem.findCommonProducts(id1, id2).print();
                        } else {
                            System.out.println("Customer 2 not found.");
                        }
                    } else {
                        System.out.println("Customer 1 not found.");
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }

        });

        System.out.println("Files loaded");
    }

    private void redirectSystemOutput() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                consoleOutputArea.append(String.valueOf((char) b));
            }
        };

        PrintStream ps = new PrintStream(out, true);
        System.setOut(ps);
        System.setErr(ps);
    }
}
