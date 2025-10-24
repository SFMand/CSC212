
import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //testing file loading
        ECommerceSystem eCSystem = new ECommerceSystem();
        eCSystem.loadFiles("assets/prodcuts.csv", "assets/customers.csv", "assets/orders.csv", "assets/reviews.csv");

        //testing print values
        eCSystem.printAllProducts();
        eCSystem.printAllCustomers();
        eCSystem.printAllOrders();

        eCSystem.searchProductId(102).printReviews();

        //testing GUI
        JFrame frame;
        frame = new JFrame("E-Commerce Management System");
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
