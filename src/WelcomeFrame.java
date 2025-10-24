
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {
    private StringBuilder sb = new StringBuilder();
    public WelcomeFrame() {
        setTitle("Welcome!");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("ECommerce System Phase 1: Admin View", SwingConstants.CENTER);
        sb.append("Yazan Almuzaini -");
        sb.append(" Abdulaziz Alnahedh -");
        sb.append(" Sultan Almandeel\n");
        JLabel namesLabel = new JLabel(sb.toString(), SwingConstants.CENTER);
        JButton continueButton = new JButton("Continue");

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminFrame().setVisible(true);
                dispose();
            }

        });

        setLayout(new BorderLayout(10,10));
        add(welcomeLabel, BorderLayout.NORTH);
        add(continueButton, BorderLayout.SOUTH);
        add(namesLabel, BorderLayout.CENTER);

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
