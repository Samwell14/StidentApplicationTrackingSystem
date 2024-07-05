import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UniversityProgramListingSystem extends JFrame {

    private Connection connection = null;
    private JPanel mainPanel, universityListPanel, universityInfoPanel;
    private JScrollPane scrollPane;
    private JButton addUniversityButton;

    public UniversityProgramListingSystem() {
        super("University Listing System");


        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));


        universityListPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        universityListPanel.setBackground(new Color(245, 245, 245));


        universityInfoPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        universityInfoPanel.setBackground(new Color(245, 245, 245));
        universityInfoPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(200, 200, 200)));


        scrollPane = new JScrollPane(universityListPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);


        try {
            connection = DatabaseConnection.getConnection();
            displayUniversityInfo();
        } catch (SQLException ex) {
            // Handle connection errors (e.g., display error message)
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


        mainPanel.add(scrollPane, BorderLayout.WEST);
        mainPanel.add(universityInfoPanel, BorderLayout.CENTER);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(mainPanel);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addUniversityButton = new JButton("Add University");
        addUniversityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentApplicationForm().setVisible(true);
            }
        });


        mainPanel.add(addUniversityButton, BorderLayout.SOUTH);


    }

    private void displayUniversityInfo() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT university_id, name, location, contactInfo FROM Universities";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            // Create university name label for list panel
            JLabel universityNameLabel = new JLabel(resultSet.getString("name"));
            universityNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            universityNameLabel.setForeground(new Color(70, 130, 180));
            universityNameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set hand cursor for potential selection

            // Create university information panel
            JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            infoPanel.setBackground(new Color(255, 255, 255));

            JLabel universityIdLabel = new JLabel("University ID:");
            JLabel nameLabel = new JLabel("Name:");
            JLabel locationLabel = new JLabel("Location:");
            JLabel contactInfoLabel = new JLabel("Contact Info:");

            JTextArea universityIdField = new JTextArea(String.valueOf(resultSet.getInt("university_id")), 1, 5);
            universityIdField.setEditable(false); // Make university ID uneditable
            universityIdField.setBackground(new Color(255, 255, 255));
            universityIdField.setForeground(new Color(70, 130, 180));
            JTextArea nameField = new JTextArea(resultSet.getString("name"), 1, 20);
            nameField.setEditable(false); // Make name uneditable
            nameField.setBackground(new Color(255, 255, 255));
            nameField.setForeground(new Color(70, 130, 180));
            JTextArea locationField = new JTextArea(resultSet.getString("location"), 3, 30);
            locationField.setEditable(false); // Make location uneditable
            locationField.setBackground(new Color(255, 255, 255));
            locationField.setForeground(new Color(70, 130, 180));
            JTextArea contactInfoField = new JTextArea(resultSet.getString("contactInfo"), 1, 20);
            contactInfoField.setEditable(false); // Make contact info uneditable
            contactInfoField.setBackground(new Color(255, 255, 255));
            contactInfoField.setForeground(new Color(70, 130, 180));

            // Add information labels and fields to the information panel
            infoPanel.add(universityIdLabel);
            infoPanel.add(universityIdField);
            infoPanel.add(nameLabel);
            infoPanel.add(nameField);
            infoPanel.add(locationLabel);
            infoPanel.add(locationField);
            infoPanel.add(contactInfoLabel);
            infoPanel.add(contactInfoField);


            universityListPanel.add(universityNameLabel);
            universityInfoPanel.add(infoPanel);

        }

        resultSet.close();
        statement.close();


        scrollPane.revalidate();
    }

}
