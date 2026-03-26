import java.awt.*;
import javax.swing.*;

public class TicketUI {

    public TicketUI(String name, String phone, String destination,
                    String date, int days, int members, int totalPrice) {

        JFrame frame = new JFrame("Travel Ticket");
        frame.setSize(520,420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Header
        JLabel title = new JLabel("✈ TRAVEL TICKET", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(52,152,219));
        title.setPreferredSize(new Dimension(520,70));

        frame.add(title, BorderLayout.NORTH);

        // Main Ticket Panel
        JPanel panel = new JPanel(new GridLayout(8,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        panel.setBackground(Color.WHITE);

        panel.add(createLabel("Passenger : " + name));
        panel.add(createLabel("Phone : " + phone));
        panel.add(createLabel("Destination : " + destination));
        panel.add(createLabel("Travel Date : " + date));
        panel.add(createLabel("Package Days : " + days));
        panel.add(createLabel("Members : " + members));
        panel.add(createPriceLabel("Total Price : ₹" + totalPrice));

        // Close Button
        JButton closeBtn = new JButton("Close Ticket");
        closeBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        closeBtn.setBackground(new Color(46,204,113));
        closeBtn.setForeground(Color.WHITE);

        closeBtn.addActionListener(e -> frame.dispose());

        panel.add(closeBtn);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Normal label style
    private JLabel createLabel(String text){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        return label;
    }

    // Price label style
    private JLabel createPriceLabel(String text){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label.setForeground(new Color(192,57,43));
        return label;
    }
}