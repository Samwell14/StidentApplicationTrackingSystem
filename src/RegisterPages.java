import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPages extends JFrame implements ActionListener , KeyListener {
    JButton registerButton = new JButton("Register");
    JPanel mainPanel = new JPanel();
    JPanel headerPanel = new JPanel();
    JLabel headerLabel = new JLabel("Welcome to APPTracker");
    JPanel formPanel = new JPanel();
    JLabel nameLabel = new JLabel("Full Name:");
    JTextField nameField = new JTextField(20);
    JTextField usernameField = new JTextField(20);
    JLabel usernameLabel = new JLabel("Username:");
    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JLabel passwordLabel = new JLabel("Password: ");
    //JTextField usernameField = new JTextField(20);

    JButton loginButton = new JButton("Login");

    public RegisterPages() {

        setTitle("Register - Higher Education and Application Tracking System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));


        headerPanel.setBackground(new Color(42, 43, 41));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new BorderLayout());

        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("consolas", Font.BOLD, 40));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.CENTER);
        headerPanel.add(headerLabel, BorderLayout.CENTER);



        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 0);



        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;

        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;

        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;

        formPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        registerButton.setBackground(new Color(42, 43, 41));
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(120, 40));
        formPanel.add(registerButton, gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton.setBackground(new Color(42, 43, 41));
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(120, 40));
        formPanel.add(loginButton, gbc);


        usernameField.addKeyListener(this);
        passwordField.addKeyListener(this);
        nameField.addKeyListener(this);
        emailField.addKeyListener(this);
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);


        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);


        add(mainPanel);
        setVisible(true);

    }

    String username;

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registerButton) {
        username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        boolean registered = AuthService.register(username, password, email);
        if (registered) {
            JOptionPane.showMessageDialog(RegisterPages.this, "Registration Successful, you can LOGIN now");
            new LoginPage();

        } else {
            JOptionPane.showMessageDialog(RegisterPages.this, "Please enter all the credentials required!");
        }}
        else if(e.getSource() == loginButton) {
            new LoginPage();
        }
    }

    public void keyTyped(KeyEvent e) {

        if (!nameField.getText().isEmpty() || !emailField.getText().isEmpty() || !passwordField.getText().isEmpty() || !usernameField.getText().isEmpty()) {
            loginButton.setEnabled(false);
        } else {
            loginButton.setEnabled(true);
        }
    }


    public void keyPressed(KeyEvent e) {
    }


    public void keyReleased(KeyEvent e) {
    }
        }



