import java.awt.*;
import javax.swing.*;

public class LoginUI {

    private JFrame frame;
    private JTextField userField;
    private JPasswordField passField;

    public LoginUI(){

        frame = new JFrame("Travel Booking Login");
        frame.setSize(500,350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        createHeader();
        createLoginPanel();

        frame.setVisible(true);
    }

    private void createHeader(){

        JLabel title = new JLabel("Travel Booking Login", JLabel.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(52,152,219));
        title.setPreferredSize(new Dimension(500,70));

        frame.add(title,BorderLayout.NORTH);
    }

    private void createLoginPanel(){

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(236,240,241));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI",Font.BOLD,18));

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI",Font.BOLD,18));

        userField = new JTextField();
        userField.setFont(new Font("Segoe UI",Font.PLAIN,18));

        passField = new JPasswordField();
        passField.setFont(new Font("Segoe UI",Font.PLAIN,18));

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Segoe UI",Font.BOLD,18));
        loginBtn.setBackground(new Color(46,204,113));
        loginBtn.setForeground(Color.WHITE);

        gbc.gridx=0; gbc.gridy=0;
        panel.add(userLabel,gbc);

        gbc.gridx=1;
        panel.add(userField,gbc);

        gbc.gridx=0; gbc.gridy=1;
        panel.add(passLabel,gbc);

        gbc.gridx=1;
        panel.add(passField,gbc);

        gbc.gridx=1; gbc.gridy=2;
        panel.add(loginBtn,gbc);

        loginBtn.addActionListener(e -> login());

        frame.add(panel,BorderLayout.CENTER);
    }

    private void login(){

        String username = userField.getText();
        String password = new String(passField.getPassword());

        // simple login check
        if(username.equals("admin") && password.equals("1234")){

            JOptionPane.showMessageDialog(frame,"Login Successful!");

            frame.dispose(); // close login window
            new TravelUI(); // open booking system

        }else{
            JOptionPane.showMessageDialog(frame,"Invalid Username or Password");
        }
    }
}