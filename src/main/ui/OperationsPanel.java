package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OperationsPanel extends JPanel {
    private ActionListener listener;
    private JLabel functionalityLabel;
    private JLabel labelInstruction;
    private JLabel label1;
    private JLabel label2;
    private JLabel labelFeedback;
    private JTextField input1;
    private JTextField input2;
    private JTextArea text;
    private JButton submit;

    public OperationsPanel(ActionListener listener) {
        this.listener = listener;

        setBackground(Color.white);
        setPreferredSize(new Dimension((PatientDatabaseGUI.WIDTH * 3 / 4), PatientDatabaseGUI.HEIGHT));

        functionalityLabel = addLabel();
        text = new JTextArea(5, 15);
        add(text);

        labelInstruction = addLabel();

        label1 = addLabel("Public Health Number: ");
        input1 = new JTextField(10);
        input1.addActionListener(listener);
        input1.setActionCommand("ADD_NUM");

        add(input1);

        label2 = addLabel("Full Name: ");

        input2 = new JTextField(15);
        input2.addActionListener(listener);
        input2.setActionCommand("ADD_NAME");
        add(input2);

        submit = new JButton("submit");
        submit.addActionListener(listener);
        submit.setActionCommand("ADD");
        add(submit);

        labelFeedback = addLabel();

        invisible();

    }

    private JLabel addLabel() {
        JLabel label = new JLabel();
        add(label);
        return label;
    }

    private JLabel addLabel(String s) {
        JLabel label = new JLabel(s);
        add(label);
        return label;
    }

    // Operations

    public void add() {
        functionalityLabel.setText("Add Patient");
        visible();
        text.setVisible(false);
        labelInstruction.setText("Please enter public health number and name of the patient:");

        label1.setText("Public Health Number: ");

        label2.setText("Full Name: ");

        submit.setActionCommand("ADD");

        labelFeedback.setText("");
    }

    public void edit() {
        functionalityLabel.setText("Edit Patient");
        visible();
        labelInstruction.setText("Please enter public health number and name of the patient:");

        label1.setText("Public Health Number: ");

        label2.setText("Full Name: ");

        submit.setActionCommand("EDIT");
        labelFeedback.setText("");
    }

    public void delete() {
        functionalityLabel.setText("Delete Patient");
        visible();
        labelInstruction.setText("Please enter public health number and name of the patient:");

        label1.setText("Public Health Number: ");

        label2.setVisible(false);
        input2.setVisible(false);

        submit.setActionCommand("DELETE");
        labelFeedback.setText("");
    }

    public void view(String s) {
        functionalityLabel.setText("");
        invisible();
//        labelInstruction.setText("Patient Records:");
        text.setVisible(true);
        text.setText("Patient Records: \n" +  s);
    }

    private void invisible() {
        labelInstruction.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        input1.setVisible(false);
        input2.setVisible(false);
        submit.setVisible(false);
        text.setVisible(false);
    }

    private void visible() {
        labelInstruction.setVisible(true);
        label1.setVisible(true);
        label2.setVisible(true);
        input1.setVisible(true);
        input2.setVisible(true);
        submit.setVisible(true);
    }

    public String getFullName() {
        return input2.getText();
    }

    public String getPublicHealthNumber() {
        return input1.getText();
    }

    public void feedback(String s) {
        labelFeedback.setText(s);
    }

    public void clear() {
        input1.setText("");
        input2.setText("");
    }

    public void save() {
        add(new JLabel("Would you prefer to save this database?"));
        JButton no = new JButton("No");
        no.addActionListener(listener);
        no.setActionCommand("QUIT");
        add(no);

        JButton yes = new JButton("Yes");
        yes.addActionListener(listener);
        yes.setActionCommand("SAVE_QUIT");
        add(yes);

    }

}
