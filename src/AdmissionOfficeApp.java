import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdmissionOfficeApp extends JFrame {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/admissionDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Samii@122";

    private JComboBox<String> studentComboBox;
    private JComboBox<String> statusComboBox;
    private JButton updateButton;

    public AdmissionOfficeApp() {
        setTitle("Admission Office - Update Application Status");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));


        JLabel studentLabel = new JLabel("Select Student:");
        studentComboBox = new JComboBox<>();
        JLabel statusLabel = new JLabel("Select Status:");
        String[] statuses = { "Approved", "Rejected", "Under Review" };
        statusComboBox = new JComboBox<>(statuses);
        updateButton = new JButton("Update Status");


        add(studentLabel);
        add(studentComboBox);
        add(statusLabel);
        add(statusComboBox);
        add(new JLabel());
        add(updateButton);


        fetchStudentNames();


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateApplicationStatus();
            }
        });
        setVisible(true);
    }

    private void fetchStudentNames() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM applications1")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                studentComboBox.addItem(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateApplicationStatus() {
        String selectedStudent = (String) studentComboBox.getSelectedItem();
        String selectedStatus = (String) statusComboBox.getSelectedItem();

        if (selectedStudent == null || selectedStatus == null) {
            JOptionPane.showMessageDialog(this, "Please select a student and a status.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE applications1 SET status = ? WHERE name = ?")) {

            preparedStatement.setString(1, selectedStatus);
            preparedStatement.setString(2, selectedStudent);
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(this, "Status updated successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update status. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating data in the database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}