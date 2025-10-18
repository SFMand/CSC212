
import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, World!");

        //testing GUI
        JFrame frame;
        frame = new JFrame("E-Commerce Management System");
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        String[] testTypes = {"Order", "Review", "Product", "Customer"};
        JComboBox<String> testTypeComboBox = new JComboBox<>(testTypes);
        frame.add(testTypeComboBox);
        frame.setLocation(0, 0);
        JButton addItemButton = new JButton("Add Item");
        frame.add(addItemButton);

        JButton removeItemButton = new JButton("Remove Item");
        frame.add(removeItemButton);

        JButton saveButton = new JButton("Save to File");
        frame.add(saveButton);

        JButton loadButton = new JButton("Load from File");
        frame.add(loadButton);

        frame.setVisible(true);

    }

}
