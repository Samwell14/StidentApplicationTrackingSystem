//public class admissionAuthenticate {
//}
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admissionAuthenticate extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public admissionAuthenticate() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
        loginButton.addActionListener(new LoginActionListener());
        setVisible(true);
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            boolean authenticated = AuthService.authenticate(username, password);
            if (authenticated) {
                JOptionPane.showMessageDialog(admissionAuthenticate.this, "Login Successful");
                //new UniversityProgramListingSystem();
                new AdmissionOfficeApp().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(admissionAuthenticate.this, "Please insert a correct Email and username!");
            }
        }
    }

}