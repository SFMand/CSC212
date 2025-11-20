package gui;

import javax.swing.*;

import main.*;
import models.*;
import structures.*;
import structures.List;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 1, 1));

        JButton listProducts = new JButton("List All Products");
        JButton listCustomers = new JButton("List All Customers, Sorted Alphabetically");
        JButton listOrders = new JButton("List All Orders");
        JButton addProduct = new JButton("Add Product");
        JButton findProduct = new JButton("Find Product by ID");
        // JButton findProductName = new JButton("Find Product by Name");
        JButton removeProduct = new JButton("Remove Product by ID");
        JButton updateProduct = new JButton("Update Product Info By ID");
        JButton priceRange = new JButton("Find Products Within Price Range");
        // JButton TOSProducts = new JButton("List All Out Of Stock Products");
        JButton registerCustomer = new JButton("Register Customer");
        JButton findCustomer = new JButton("Find Customer by ID");
        JButton placeOrder = new JButton("Place Order for Customer");
        JButton customersReviews = new JButton("Find All Customers Who Reviewed a Product");
        JButton findOrder = new JButton("Find Order by ID");
        JButton topRated = new JButton("List Top Rated Products");
        JButton ordersBetweenDates = new JButton("List Orders Between Two Dates");
        // JButton customerReviews = new JButton("List a Customer's Reviews");
        // JButton commonProducts = new JButton("List Common Products");
        JButton customerOrderHistory = new JButton("List Customer's Order History");
        JButton addReview = new JButton("Add Review to Product");
        JButton updateReview = new JButton("Update Review");
        JButton avgReview = new JButton("Get Average Rating of Product");
        JButton cancelOrder = new JButton("Cancel Order");
        JButton updateOrder = new JButton("Update Order Status");

        buttonPanel.add(listProducts);
        buttonPanel.add(listCustomers);
        buttonPanel.add(listOrders);
        buttonPanel.add(new JLabel("Product Operations"));
        buttonPanel.add(addProduct);
        buttonPanel.add(findProduct);
        // buttonPanel.add(findProductName);
        buttonPanel.add(removeProduct);
        buttonPanel.add(updateProduct);
        buttonPanel.add(customersReviews);
        // buttonPanel.add(TOSProducts);
        buttonPanel.add(new JLabel("Customer Operations"));
        buttonPanel.add(registerCustomer);
        buttonPanel.add(placeOrder);
        buttonPanel.add(findCustomer);
        // buttonPanel.add(customerReviews);
        buttonPanel.add(customerOrderHistory);
        buttonPanel.add(new JLabel("Order Operations"));
        buttonPanel.add(findOrder);
        buttonPanel.add(updateOrder);
        buttonPanel.add(cancelOrder);
        buttonPanel.add(new JLabel("Review Operations"));
        buttonPanel.add(addReview);
        buttonPanel.add(updateReview);
        buttonPanel.add(avgReview);
        buttonPanel.add(new JLabel("Other Requirements"));
        // buttonPanel.add(commonProducts);
        buttonPanel.add(priceRange);
        buttonPanel.add(topRated);
        buttonPanel.add(ordersBetweenDates);

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
                System.out.println("--- Listing All Customers (Sorted by Name) ---");
                eCSystem.printAllCustomers();
            }

        });

        listOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("--- Listing All Orders ---");
                eCSystem.printAllOrders();
            }

        });

        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String name = JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Name:", "Add Product",
                            JOptionPane.QUESTION_MESSAGE);
                    double price = Double.parseDouble(JOptionPane.showInputDialog(AdminFrame.this,
                            "Enter Product Price:", "Add Product", JOptionPane.QUESTION_MESSAGE));
                    int stock = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Stock:",
                            "Add Product", JOptionPane.QUESTION_MESSAGE));
                    if (name != null) {
                        eCSystem.addProduct(new Product(name, price, stock));
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }

        });
        /*
         * findProductName.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * String name = JOptionPane.showInputDialog(AdminFrame.this,
         * "Enter Product Name:", "Find Product",
         * JOptionPane.QUESTION_MESSAGE);
         * System.out.println("--- Searching for Product w/Name: " + name + " ---");
         * Product p = eCSystem.searchProductName(name);
         * if (p != null) {
         * System.out.println(p);
         * } else {
         * System.out.println("Product not found.");
         * }
         * 
         * }
         * 
         * });
         * 
         */
        removeProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Id: ",
                            "Remove Product", JOptionPane.QUESTION_MESSAGE));
                    if (eCSystem.removeProduct(id)) {
                        System.out.println("Product Removed Succesfully");
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
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product ID:",
                            "Find Product", JOptionPane.QUESTION_MESSAGE));
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
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this,
                            "Enter Product Id to be updated:", "Update Product", JOptionPane.QUESTION_MESSAGE));
                    String name = JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Name:", "Update Product",
                            JOptionPane.QUESTION_MESSAGE);
                    double price = Double.parseDouble(JOptionPane.showInputDialog(AdminFrame.this,
                            "Enter Product Price:", "Update Product", JOptionPane.QUESTION_MESSAGE));
                    int stock = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Stock:",
                            "Update Product", JOptionPane.QUESTION_MESSAGE));
                    if (name != null) {
                        eCSystem.updateProduct(id, name, price, stock);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }

        });

        customersReviews.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Id",
                            "Find Customers", JOptionPane.QUESTION_MESSAGE));
                    System.out.println("--- Searching for Customer who reviewed Product ID: " + id + " ---");
                    eCSystem.productReviewers(id).traverse(TraverseOrder.IN_ORDER);

                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());

                }
            }

        });

        priceRange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double min = Double.parseDouble(JOptionPane.showInputDialog(AdminFrame.this, "Enter minimum price",
                            "Price Range Query", JOptionPane.QUESTION_MESSAGE));
                    Double max = Double.parseDouble(JOptionPane.showInputDialog(AdminFrame.this, "Enter maximum price",
                            "Price Range Query", JOptionPane.QUESTION_MESSAGE));
                    if (min <= max) {
                        System.out.printf("--- Products Within: [%.2f - %.2f] ---\n", min, max);
                        eCSystem.getProductsInRange(min, max).print();
                    } else {
                        System.out.println("Invalid Range");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }

            }

        });
        /*
         * TOSProducts.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * System.out.println("--- Listing All Out Of Stock Products ---");
         * eCSystem.trackOutOfStockProducts().print();
         * }
         * 
         * });
         */

        registerCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer Name:", "Register Customer",
                        JOptionPane.QUESTION_MESSAGE);
                if (name != null) {
                    String email = JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer Email:",
                            "Register Customer", JOptionPane.QUESTION_MESSAGE);
                    if (email != null) {
                        if (!eCSystem.registerCustomer(new Customer(name, email))) {
                            System.out.println("Customer registration failed, name is taken");
                        }

                    }
                }

            }

        });

        findCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer ID:",
                            "Find Customer", JOptionPane.QUESTION_MESSAGE));
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
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer ID",
                            "Place Order", JOptionPane.QUESTION_MESSAGE));
                    Customer c = eCSystem.searchCustomerId(id);
                    if (c != null) {
                        List<Product> products = new LinkedList<>();
                        do {
                            id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product Id",
                                    "Place Order", JOptionPane.QUESTION_MESSAGE));
                            Product p = eCSystem.searchProductId(id);
                            if (p != null && p.getStock() > 0) {
                                products.insert(p);
                            } else {
                                JOptionPane.showMessageDialog(AdminFrame.this, "Product Id Invalid, or Stock is Zero",
                                        "Place Order", JOptionPane.ERROR_MESSAGE);
                            }
                        } while (JOptionPane.showConfirmDialog(AdminFrame.this, "Continue Adding Products?",
                                "Place Order", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION);
                        if (!products.empty()) {
                            eCSystem.placeOrder(c, products);
                        } else {
                            JOptionPane.showMessageDialog(AdminFrame.this, "No Products were added", "Place Order",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }
        });

        findOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Order ID:",
                            "Find Order", JOptionPane.QUESTION_MESSAGE));
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

        updateOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Order ID:",
                            "Update Order Status", JOptionPane.QUESTION_MESSAGE));
                    Order o = eCSystem.searchOrderId(id);
                    if (o != null) {
                        String newStatus = JOptionPane.showInputDialog(AdminFrame.this, "Enter New Status:",
                                "Update Order Status", JOptionPane.QUESTION_MESSAGE);
                        if (newStatus != null) {
                            o.updateStatus(newStatus);
                            System.out.println("--- Order " + id + " Status Updated ---");
                        }
                    } else {
                        System.out.println("Order not found.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }
        });

        cancelOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Order ID:",
                            "Cancel Order", JOptionPane.QUESTION_MESSAGE));
                    Order o = eCSystem.searchOrderId(id);
                    if (o != null) {
                        o.cancelOrder();
                        System.out.println("--- Order " + id + " Cancelled ---");
                    } else {
                        System.out.println("Order not found.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }
        });

        addReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cID = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer ID:",
                            "Add Review", JOptionPane.QUESTION_MESSAGE));
                    Customer c = eCSystem.searchCustomerId(cID);
                    if (c != null) {
                        int pID = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product ID:",
                                "Add Review", JOptionPane.QUESTION_MESSAGE));
                        Product p = eCSystem.searchProductId(pID);
                        if (p != null) {
                            String comment = JOptionPane.showInputDialog(AdminFrame.this, "Enter Comment:",
                                    "Add Review", JOptionPane.QUESTION_MESSAGE);
                            int rating = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this,
                                    "Enter Rating (1-5):", "Add Review", JOptionPane.QUESTION_MESSAGE));
                            if (comment != null) {
                                Review r = new Review(comment, rating, cID);
                                eCSystem.addReviewToProduct(p, r);
                                System.out
                                        .println("--- Review " + r.getReviewId() + " added to " + p.getName() + " ---");
                            }
                        } else {
                            System.out.println("Product not found.");
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }
        });
        /*
         * updateReview.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * try {
         * int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this,
         * "Enter Review ID:",
         * "Update Review", JOptionPane.QUESTION_MESSAGE));
         * Review r = eCSystem.searchReviewId(id);
         * if (r != null) {
         * String comment = JOptionPane.showInputDialog(AdminFrame.this,
         * "Enter New Comment:",
         * "Update Review", JOptionPane.QUESTION_MESSAGE);
         * int rating = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this,
         * "Enter New Rating:",
         * "Update Review", JOptionPane.QUESTION_MESSAGE));
         * if (comment != null) {
         * System.out.println("--- Review " + id + " Updated ---");
         * r.editReview(comment, rating);
         * }
         * } else {
         * System.out.println("Review not found.");
         * }
         * } catch (NumberFormatException ex) {
         * System.out.println("Invalid Input: " + ex.getMessage());
         * }
         * }
         * 
         * });
         */

        avgReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Product ID:",
                            "Get Average Rating", JOptionPane.QUESTION_MESSAGE));
                    Product p = eCSystem.searchProductId(id);
                    if (p != null) {
                        System.out.println("--- Average Rating for " + p.getName() + " ---");
                        System.out.printf("Average Rating: %.2f\n", p.getAverageRating());
                    } else {
                        System.out.println("Product not found.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input: " + ex.getMessage());
                }
            }
        });

        topRated.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("--- Listing Top 3 Rated Products ---");
                eCSystem.getTopRatedProducts().print();
            }

        });

        ordersBetweenDates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate startDate = LocalDate.parse(JOptionPane.showInputDialog(AdminFrame.this,
                            "Enter Start Date (YYYY-MM-DD):", "Orders Between Dates", JOptionPane.QUESTION_MESSAGE));
                    LocalDate endDate = LocalDate.parse(JOptionPane.showInputDialog(AdminFrame.this,
                            "Enter End Date (YYYY-MM-DD):", "Orders Between Dates", JOptionPane.QUESTION_MESSAGE));

                    System.out.println("--- Listing Orders Between " + startDate + " and " + endDate + " ---");
                    List<Order> orders = eCSystem.getOrdersBetweenDates(startDate, endDate);

                    if (orders.empty()) {
                        System.out.println("No Orders Found.");
                    } else {
                        orders.print();
                    }

                } catch (java.time.format.DateTimeParseException ex) {
                    System.out.println("Invalid Date Format. Use YYYY-MM-DD. " + ex.getMessage());
                }
            }
        });
        /*
         * customerReviews.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * try {
         * int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this,
         * "Enter Customer ID:", "Find Customer", JOptionPane.QUESTION_MESSAGE));
         * Customer c = eCSystem.searchCustomerName(id);
         * 
         * if (c != null) {
         * System.out.println("--- Listing All Reviews of Customer with Id: " + id +
         * " ---");
         * eCSystem.getCustomerReviews(c).print();
         * } else {
         * System.out.println("Customer not Found.");
         * }
         * } catch (NumberFormatException ex) {
         * System.out.println("Invalid Input" + ex.getMessage());
         * }
         * 
         * }
         * 
         * });
         */
        /*
         * commonProducts.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * try {
         * int id1 = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this,
         * "Enter Customer 1 Id:", "Find Customers", JOptionPane.QUESTION_MESSAGE));
         * if (eCSystem.searchCustomerId(id1) != null) {
         * int id2 = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this,
         * "Enter Customer 2 Id:", "Find Customers", JOptionPane.QUESTION_MESSAGE));
         * if (eCSystem.searchCustomerId(id2) != null) {
         * System.out.println("--- Listing All Products Ordered by " + id1 + " / " + id2
         * + "---");
         * eCSystem.findCommonProducts(id1, id2).print();
         * } else {
         * System.out.println("Customer 2 not found.");
         * }
         * } else {
         * System.out.println("Customer 1 not found.");
         * }
         * 
         * } catch (NumberFormatException ex) {
         * System.out.println("Invalid Input: " + ex.getMessage());
         * }
         * }
         * 
         * });
         */

        customerOrderHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Enter Customer Id",
                            "Order History", JOptionPane.QUESTION_MESSAGE));
                    Customer c = eCSystem.searchCustomerId(id);
                    if (c != null) {
                        System.out.println("--- Listing All Order of Customer with Id: " + id + " ---");
                        c.getOrderHistory().print();
                    } else {
                        System.out.println("Customer not found.");
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
