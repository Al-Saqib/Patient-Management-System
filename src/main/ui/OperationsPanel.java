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

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;


        functionalityLabel = addLabel(c);

        c.gridy = 2;
        text = new JTextArea(5, 15);
        add(text, c);

        labelInstruction = addLabel(c);

        label1 = addLabel("Public Health Number: ", c);
        input1 = new JTextField(10);
        input1.addActionListener(listener);
        input1.setActionCommand("ADD_NUM");

        add(input1, c);

        label2 = addLabel("Full Name: ", c);

        input2 = new JTextField(15);
        input2.addActionListener(listener);
        input2.setActionCommand("ADD_NAME");
        add(input2, c);

        submit = new JButton("submit");
        submit.addActionListener(listener);
        submit.setActionCommand("ADD");
        add(submit, c);

        labelFeedback = addLabel(c);

        invisible();

    }

    private JLabel addLabel(GridBagConstraints c) {
        JLabel label = new JLabel();
        add(label, c);
        return label;
    }

    private JLabel addLabel(String s, GridBagConstraints c) {
        JLabel label = new JLabel(s);
        add(label, c);
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

    public void saveQuit() {
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

    public void save() {
        invisible();
        labelFeedback.setVisible(true);
    }

}
