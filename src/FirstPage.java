import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPage extends JFrame {

    public FirstPage() {
        setTitle("University Admission Portal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        JLabel titleLabel = new JLabel("Welcome to AppTracker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0x2E86C1));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);


        JButton firstYearButton = new JButton("First Year Student");
        firstYearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        firstYearButton.setBackground(new Color(0x5DADE2));
        firstYearButton.setForeground(Color.WHITE);
        firstYearButton.setFocusPainted(false);
        firstYearButton.addActionListener(new FirstYearButtonListener());

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(firstYearButton, gbc);


        JButton transferButton = new JButton("Transfer Student");
        transferButton.setFont(new Font("Arial", Font.PLAIN, 18));
        transferButton.setBackground(new Color(0x48C9B0));
        transferButton.setForeground(Color.WHITE);
        transferButton.setFocusPainted(false);
        transferButton.addActionListener(new TransferButtonListener());

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(transferButton, gbc);

        JButton admission = new JButton("Admission Office");
        admission.setFont(new Font("Arial", Font.PLAIN, 18));
        admission.setBackground(new Color(0x5DADE2));
        admission.setForeground(Color.WHITE);
        admission.setFocusPainted(false);
        admission.addActionListener(new admissionButtonListener());

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(admission, gbc);



        gbc.gridy = 3;
        gbc.gridheight = 1;
        mainPanel.add(Box.createVerticalStrut(30), gbc);

        add(mainPanel);
        setVisible(true);
    }

    private class FirstYearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            new RegisterPages();
        }
    }

    private class TransferButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            new RegisterPages();
        }
    }

    private class admissionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            new admissionAuthenticate();
        }
    }


}