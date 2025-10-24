
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class AdminFrame extends JFrame {

    private JTextArea consoleOutputArea;
    private ECommerceSystem eCSystem;

    public AdminFrame() {
        setTitle("Admin View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        eCSystem = new ECommerceSystem();

        eCSystem.loadFiles();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1, 5, 5));

        JButton btnListProducts = new JButton("List All Products");
        JButton btnListCustomers = new JButton("List All Customers");
        JButton btnAddProduct = new JButton("Add Product"); //missing implementation
        JButton btnFindProduct = new JButton("Find Product by ID");

        buttonPanel.add(btnListProducts);
        buttonPanel.add(btnListCustomers);
        buttonPanel.add(btnAddProduct);
        buttonPanel.add(btnFindProduct);
       
        //need buttons for all functions

        consoleOutputArea = new JTextArea();
        consoleOutputArea.setEditable(false);
        consoleOutputArea.setFont(new Font("Consolas", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(consoleOutputArea);

        setLayout(new BorderLayout(10, 10));
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        redirectSystemStreams();

        btnListProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("--- Listing All Products ---");
                eCSystem.printAllProducts();

            }
        });

        btnListCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("--- Listing All Customers ---");
                eCSystem.printAllCustomers();
            }

        });


        btnFindProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = JOptionPane.showInputDialog(AdminFrame.this, "Enter Product ID:", "Find Product", JOptionPane.QUESTION_MESSAGE);

                if (!idString.isBlank()) {
                    try {
                        int id = Integer.parseInt(idString);
                        System.out.println("--- Searching for Product ID: " + id + " ---");
                        Product p = eCSystem.searchProductId(id);
                        if (p != null) {
                            System.out.println(p);
                        } else {
                            System.out.println("Product not found.");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid ID: Please enter a number.");
                    }
                }
            }
        });

        System.out.println("Files loaded");
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

                consoleOutputArea.append(String.valueOf((char) b));
                // Auto-scroll to the bottom
                consoleOutputArea.setCaretPosition(consoleOutputArea.getDocument().getLength());
            }
        };

        PrintStream ps = new PrintStream(out, true);
        System.setOut(ps);
        System.setErr(ps);
    }
}
