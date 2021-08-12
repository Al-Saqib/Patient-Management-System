package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


// Represents a operations panel that displays the required operations performed in the app
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



    // EFFECTS: Constructs the operations panel with the required fields and dimensions
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

//        labelInstruction = addLabel(c);
//
//        label1 = addLabel("Public Health Number: ", c);
//        input1 = new JTextField(10);
//        input1.addActionListener(listener);
//        input1.setActionCommand("ADD_NUM");
//
//        add(input1, c);
//
//        label2 = addLabel("Full Name: ", c);
//
//        input2 = new JTextField(15);
//        input2.addActionListener(listener);
//        input2.setActionCommand("ADD_NAME");
//        add(input2, c);

        settingUpLabels();

        submit = new JButton("submit");
        submit.addActionListener(listener);
        submit.setActionCommand("ADD");
        add(submit, c);

        labelFeedback = addLabel(c);

        invisible();

    }


    // EFFECTS: sets up the labels that appear in add, edit and delete functions
    private void settingUpLabels() {
        GridBagConstraints c = new GridBagConstraints();

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
    }


    // MODIFIES: this
    // EFFECTS: adds a label to the operations panel
    private JLabel addLabel(GridBagConstraints c) {
        JLabel label = new JLabel();
        add(label, c);
        return label;
    }


    // MODIFIES: this
    // EFFECTS: adds a label with string to the operations panel
    private JLabel addLabel(String s, GridBagConstraints c) {
        JLabel label = new JLabel(s);
        add(label, c);
        return label;
    }








    // MODIFIES: this
    // EFFECTS: adds a patient to patient records

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



    // MODIFIES: this
    // EFFECTS: edits a patient in the patient records

    public void edit() {
        functionalityLabel.setText("Edit Patient");
        visible();
        labelInstruction.setText("Please enter public health number and name of the patient:");

        label1.setText("Public Health Number: ");

        label2.setText("Full Name: ");

        submit.setActionCommand("EDIT");
        labelFeedback.setText("");
    }





    // MODIFIES: this
    // EFFECTS: deletes a patient in the patient records
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





    // EFFECTS: shows patients in the patient records
    public void view(String s) {
        functionalityLabel.setText("");
        invisible();
        text.setVisible(true);
        text.setText("Patient Records: \n" +  s);
    }




    // EFFECTS: makes the patient records invisible
    private void invisible() {
        labelInstruction.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        input1.setVisible(false);
        input2.setVisible(false);
        submit.setVisible(false);
        text.setVisible(false);
    }



    // EFFECTS: makes the patient records visible
    private void visible() {
        labelInstruction.setVisible(true);
        label1.setVisible(true);
        label2.setVisible(true);
        input1.setVisible(true);
        input2.setVisible(true);
        submit.setVisible(true);
    }




    // EFFECTS: gets full name of patient
    public String getFullName() {
        return input2.getText();
    }



    // EFFECTS: gets public health number of patient
    public String getPublicHealthNumber() {
        return input1.getText();
    }




    // EFFECTS: gives feedback for commands
    public void feedback(String s) {
        labelFeedback.setText(s);
    }




    // EFFECTS: clears the labels for further operations
    public void clear() {
        input1.setText("");
        input2.setText("");
    }




    // EFFECTS: prompts user to save if file has not been saved. If yes, saves file and quits.
    // If no, quits without saving
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




    // EFFECTS:
    public void save() {
        invisible();
        labelFeedback.setVisible(true);
    }

}
