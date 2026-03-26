import java.awt.*;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.*;

public class TravelUI {

    private JFrame frame;

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField membersField;

    private JComboBox<String> destinationBox;

    private JSpinner fromDateChooser;
    private JSpinner toDateChooser;

    private JLabel imageLabel;
    private JLabel priceLabel;
    private JLabel totalPriceLabel;

    private final BookingService bookingService = new BookingService();

    public TravelUI(){

        frame = new JFrame("Travel Booking System");
        frame.setSize(1200,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        createHeader();
        createMainPanel();

        frame.setVisible(true);
    }

    private void createHeader(){

        JLabel title = new JLabel("✈ Travel Booking System", JLabel.CENTER);

        title.setFont(new Font("Segoe UI",Font.BOLD,36));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(52,152,219));
        title.setPreferredSize(new Dimension(1200,90));

        frame.add(title,BorderLayout.NORTH);
    }

    private void createMainPanel(){

        JPanel mainPanel = new JPanel(new GridLayout(1,2));

        mainPanel.add(createFormPanel());
        mainPanel.add(createImagePanel());

        frame.add(mainPanel,BorderLayout.CENTER);
    }

    private JPanel createFormPanel(){

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245,245,245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI",Font.BOLD,18);
        Font fieldFont = new Font("Segoe UI",Font.PLAIN,18);

        JLabel nameLabel = new JLabel("Passenger Name:");
        nameLabel.setFont(labelFont);

        nameField = new JTextField();
        nameField.setFont(fieldFont);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(labelFont);

        phoneField = new JTextField();
        phoneField.setFont(fieldFont);

        JLabel destLabel = new JLabel("Destination:");
        destLabel.setFont(labelFont);

        String destinations[] = {"Goa","Delhi","Dubai"};
        destinationBox = new JComboBox<>(destinations);
        destinationBox.setFont(fieldFont);

        JLabel fromLabel = new JLabel("From Date:");
        fromLabel.setFont(labelFont);

        fromDateChooser = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor fromEditor =
                new JSpinner.DateEditor(fromDateChooser,"dd-MM-yyyy");
        fromDateChooser.setEditor(fromEditor);

        JLabel toLabel = new JLabel("To Date:");
        toLabel.setFont(labelFont);

        toDateChooser = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor toEditor =
                new JSpinner.DateEditor(toDateChooser,"dd-MM-yyyy");
        toDateChooser.setEditor(toEditor);

        JLabel membersLabel = new JLabel("Members:");
        membersLabel.setFont(labelFont);

        membersField = new JTextField();
        membersField.setFont(fieldFont);

        totalPriceLabel = new JLabel("Total Price: ₹0");
        totalPriceLabel.setFont(new Font("Segoe UI",Font.BOLD,22));
        totalPriceLabel.setForeground(new Color(192,57,43));

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setFont(new Font("Segoe UI",Font.BOLD,20));
        bookButton.setBackground(new Color(46,204,113));
        bookButton.setForeground(Color.WHITE);

        gbc.gridx=0; gbc.gridy=0;
        panel.add(nameLabel,gbc);
        gbc.gridx=1;
        panel.add(nameField,gbc);

        gbc.gridx=0; gbc.gridy=1;
        panel.add(phoneLabel,gbc);
        gbc.gridx=1;
        panel.add(phoneField,gbc);

        gbc.gridx=0; gbc.gridy=2;
        panel.add(destLabel,gbc);
        gbc.gridx=1;
        panel.add(destinationBox,gbc);

        gbc.gridx=0; gbc.gridy=3;
        panel.add(fromLabel,gbc);
        gbc.gridx=1;
        panel.add(fromDateChooser,gbc);

        gbc.gridx=0; gbc.gridy=4;
        panel.add(toLabel,gbc);
        gbc.gridx=1;
        panel.add(toDateChooser,gbc);

        gbc.gridx=0; gbc.gridy=5;
        panel.add(membersLabel,gbc);
        gbc.gridx=1;
        panel.add(membersField,gbc);

        gbc.gridx=0; gbc.gridy=6;
        gbc.gridwidth=2;
        panel.add(totalPriceLabel,gbc);

        gbc.gridy=7;
        panel.add(bookButton,gbc);

        destinationBox.addActionListener(e -> {
            updateImage();
            calculateTotal();
        });

        fromDateChooser.addChangeListener(e -> calculateTotal());
        toDateChooser.addChangeListener(e -> calculateTotal());
        membersField.addActionListener(e -> calculateTotal());

        bookButton.addActionListener(e -> bookTicket());

        return panel;
    }

    private JPanel createImagePanel(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        priceLabel = new JLabel("", JLabel.CENTER);
        priceLabel.setFont(new Font("Segoe UI",Font.BOLD,30));
        priceLabel.setForeground(new Color(192,57,43));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        updateImage();

        panel.add(priceLabel,BorderLayout.NORTH);
        panel.add(imageLabel,BorderLayout.CENTER);

        return panel;
    }

    private void updateImage(){

        String destination = (String) destinationBox.getSelectedItem();

        int price = bookingService.getPrice(destination);
        priceLabel.setText("Price: ₹" + price);

        String fileName = destination.toLowerCase() + ".jpg";

        ImageIcon icon = new ImageIcon(fileName);

        Image img = icon.getImage().getScaledInstance(
                650,500,Image.SCALE_SMOOTH);

        imageLabel.setIcon(new ImageIcon(img));
    }

    private int calculateDays(){

        try{

            Date from = (Date) fromDateChooser.getValue();
            Date to = (Date) toDateChooser.getValue();

            long days = ChronoUnit.DAYS.between(
                    from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            );

            if(days <= 0) return 1;

            return (int) days;

        }
        catch(Exception e){
            return 1;
        }
    }

    private void calculateTotal(){

        try{

            String destination = (String) destinationBox.getSelectedItem();
            int price = bookingService.getPrice(destination);

            int members = Integer.parseInt(membersField.getText());
            int days = calculateDays();

            int total = price * members * days;

            totalPriceLabel.setText("Total Price: ₹" + total + " (" + days + " days)");

        }
        catch(Exception e){

            totalPriceLabel.setText("Total Price: ₹0");

        }
    }

    private void bookTicket(){

        String name = nameField.getText();
        String phone = phoneField.getText();

        String destination = (String) destinationBox.getSelectedItem();

        int price = bookingService.getPrice(destination);

        int members = Integer.parseInt(membersField.getText());
        int days = calculateDays();

        int total = price * members * days;

        bookingService.saveBooking(name,phone,destination,"Trip",days,members,total);

        new TicketUI(name,phone,destination,"Trip",days,members,total);
    }
}