import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class StudentApplicationForm extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField fieldOfStudyField;
    private JTextArea transcriptArea;
    private JTextArea entranceExamArea;
    private File transcriptFile;
    private File entranceExamFile;

    String[] universities = { "Select University to apply to ","Harvard University", "Stanford University", "MIT" };

    private JComboBox<String> uvlist;


    public StudentApplicationForm() {
        setTitle("Student Application Form");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setSize(100, 200);
        panel.setBackground(Color.white);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(20, 20));
        emailField = new JTextField();
        fieldOfStudyField = new JTextField();
        uvlist = new JComboBox<>(universities);
        uvlist.setSize(50, 50);
        uvlist.setSelectedIndex(0);



        transcriptArea = new JTextArea();
        transcriptArea.setEditable(false);
        entranceExamArea = new JTextArea();
        entranceExamArea.setEditable(false);

        JButton transcriptButton = new JButton("Upload Transcript");
        transcriptButton.addActionListener(new TranscriptButtonListener());

        JButton entranceExamButton = new JButton("Upload Entrance Exam");
        entranceExamButton.addActionListener(new EntranceExamButtonListener());

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());

        panel.add(uvlist);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Field of Study:"));
        panel.add(fieldOfStudyField);
        panel.add(new JLabel("Transcript:"));
        panel.add(transcriptArea);
        panel.add(transcriptButton);
        panel.add(new JLabel("Entrance Exam:"));
        panel.add(entranceExamArea);
        panel.add(entranceExamButton);
        panel.add(submitButton);

        add(panel);
    }

    private class TranscriptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(StudentApplicationForm.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                transcriptFile = fileChooser.getSelectedFile();
                transcriptArea.setText(transcriptFile.getAbsolutePath());
            }
        }
    }

    private class EntranceExamButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(StudentApplicationForm.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                entranceExamFile = fileChooser.getSelectedFile();
                entranceExamArea.setText(entranceExamFile.getAbsolutePath());
            }
        }
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String fieldOfStudy = fieldOfStudyField.getText();

            if (name.isEmpty() || email.isEmpty() || fieldOfStudy.isEmpty() || transcriptFile == null || entranceExamFile == null) {
                JOptionPane.showMessageDialog(StudentApplicationForm.this, "Please fill in all fields and upload all files.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            JOptionPane.showMessageDialog(StudentApplicationForm.this, "Application submitted successfully!");
        }
    }


}