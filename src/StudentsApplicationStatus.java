import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentsApplicationStatus extends JFrame {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/admissionDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Samii@122";

    public StudentsApplicationStatus() {
        setTitle("Student Application Status");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);


        model.addColumn("Student ID");
        model.addColumn("Name");
        model.addColumn("Status");


        fetchApplicationData(model);

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void fetchApplicationData(DefaultTableModel model) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM applications1")) {

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String status = resultSet.getString("status");

                model.addRow(new Object[]{studentId, name, status});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}