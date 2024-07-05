import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.*;
public class TransferStudentForm extends JFrame {
    private JTextField nameField, emailField, fieldOfStudyField, previousUniversityField, gpaField;
    private JButton transcriptButton, entranceExamButton, submitButton;
    private JFileChooser fileChooser;
    private String transcriptFilePath, entranceExamFilePath;

    public TransferStudentForm() {
        setTitle("Transfer Student Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);


        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);


        panel.add(new JLabel("Field of Study:"));
        fieldOfStudyField = new JTextField();
        panel.add(fieldOfStudyField);


        panel.add(new JLabel("Previous University:"));
        previousUniversityField = new JTextField();
        panel.add(previousUniversityField);


        panel.add(new JLabel("GPA:"));
        gpaField = new JTextField();
        panel.add(gpaField);


        panel.add(new JLabel("Transcript:"));
        transcriptButton = new JButton("Choose File");
        transcriptButton.addActionListener(e -> selectFile("transcript"));
        panel.add(transcriptButton);


        panel.add(new JLabel("Entrance Exam:"));
        entranceExamButton = new JButton("Choose File");
        entranceExamButton.addActionListener(e -> selectFile("entrance_exam"));
        panel.add(entranceExamButton);


        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitForm());
        panel.add(new JLabel());
        panel.add(submitButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void selectFile(String type) {
        fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (type.equals("transcript")) {
                transcriptFilePath = file.getAbsolutePath();
            } else {
                entranceExamFilePath = file.getAbsolutePath();
            }
        }
    }

    private void submitForm() {
        String name = nameField.getText();
        String email = emailField.getText();
        String fieldOfStudy = fieldOfStudyField.getText();
        String previousUniversity = previousUniversityField.getText();
        String gpa = gpaField.getText();


        try {

            Class.forName("com.mysql.jdbc.Driver");


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admissionDB", "root", "Samii@122");


            String sql = "INSERT INTO TransferStudents (Name, Email, FieldOfStudy, PreviousUniversity, GPA) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);


            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, fieldOfStudy);
            statement.setString(4, previousUniversity);
            statement.setString(5, gpa);


            statement.executeUpdate();


            statement.close();
            connection.close();

            JOptionPane.showMessageDialog(this, "Application submitted successfully!");
            new LoginPage();
            clearForm();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database" );
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting data , Please Reinsert your Informations");
        }
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        fieldOfStudyField.setText("");
        previousUniversityField.setText("");
        gpaField.setText("");
        transcriptFilePath = "";
        entranceExamFilePath = "";
    }


}