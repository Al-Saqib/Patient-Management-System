package ui;

import model.Patient;
import model.PatientRecords;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.awt.Image;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

// graphical user interface implementation is inspired by SpaceInvaders
// project as demonstrated in CPSC 210 class


// Represents a patient database graphical user interface with width and height

public class PatientDatabaseGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private static final String JSON_STORE = "./data/database.json";

    private JsonWriter jsonWriter;
    private boolean changesSaved;
    private JsonReader jsonReader;
    private PatientRecords patientRecords;

    private CommandPanel cp;
    private OperationsPanel op;

    private SoundPlayer soundPlayer;






    // EFFECTS: Constructs a patient database graphical user interface
    public PatientDatabaseGUI() {



        // createWindow
        super("Patient Database");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                quit();
            }
        });
        setPreferredSize(new Dimension(WIDTH, HEIGHT));



        // Components (Panels: title panel, command panel, save/load panel, crud panels)
        cp = new CommandPanel(this);
        op = new OperationsPanel(this);
        add(cp, BorderLayout.WEST);
        add(op, BorderLayout.EAST);

        // Event Handling



        // Resizing Window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        initialize();
    }








    // EFFECTS: initializes the fields contained in the method
    private void initialize() {
        patientRecords = new PatientRecords();
        changesSaved = true;
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        soundPlayer = new SoundPlayer();
        load();
    }



    //EFFECTS: This is called when the the JButton btn is clicked
    // provides the specified results for the actions performed on the GUI
    public void actionPerformed(ActionEvent e) {
        playSound();
        generateGUI(e);
//        ImageIcon icon;
        dataPersistenceFunctions(e);
        if (e.getActionCommand().equals("VIEW")) {
            commandView();
//            icon = new ImageIcon("C:\\Users\\saqib\\CPSC210Summerlabs\\quiz2partb\\kermit.jpg)");
//            this.setIconImage(icon.getImage());
        }
        if (e.getActionCommand().equals("ADD")) {
            commandAdd();
        }
        if (e.getActionCommand().equals("EDIT")) {
            commandEdit();
        }
        if (e.getActionCommand().equals("DELETE")) {
            commandDelete();
        }
        if (e.getActionCommand().equals("QUIT")) {
            System.exit(0);
        }
    }




    // EFFECTS: generates graphical user interface
    private void generateGUI(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_GUI")) {
            op.add();
        }
        if (e.getActionCommand().equals("DELETE_GUI")) {
            viewHelper();
            op.delete();
        }
        if (e.getActionCommand().equals("EDIT_GUI")) {
            viewHelper();
            op.edit();
        }
    }


    // EFFECTS: provides data persistence functionality
    private void dataPersistenceFunctions(ActionEvent e) {
        if (e.getActionCommand().equals("SAVE")) {
            save();
        }
        if (e.getActionCommand().equals("LOAD")) {
            load();
        }
        if (e.getActionCommand().equals("SAVE_QUIT")) {
            save();
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for name and public health number of patient and
    // adds to patient records
    private void commandAdd() {
        int publicHealthNumber = 0;
        String fullName = "";

        try {
            publicHealthNumber = Integer.parseInt(op.getPublicHealthNumber());
        } catch (NumberFormatException ex) {
            //
        }
        fullName = op.getFullName();

        boolean validInput = validateInput(publicHealthNumber, fullName);

        if (validInput) {

            if (patientRecords.getRecords().containsKey(publicHealthNumber)) {
                op.feedback("Patient already exists. Use edit function to change personal information.");
                op.clear();
                return;
            }

            patientRecords.addPatient(publicHealthNumber, fullName);

            op.feedback("\nPatient " + fullName + " with number " + publicHealthNumber + " has been added.");
            op.clear();

            changesSaved = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: edits full name of the patient in the patient records
    private void commandEdit() {
        int publicHealthNumber = 0;
        String fullName = "";

        viewHelper();

        try {
            publicHealthNumber = Integer.parseInt(op.getPublicHealthNumber());
        } catch (NumberFormatException ex) {
            //
        }
        fullName = op.getFullName();

        boolean validInput = validateInput(publicHealthNumber, fullName);

        if (validInput) {
            if (!patientRecords.getRecords().containsKey(publicHealthNumber)) {
                op.feedback("Patient doesn't exist. Use add function to add patient.");
                op.clear();
                return;
            }

            patientRecords.editPatient(fullName, publicHealthNumber);

            op.feedback("\nPatient " + fullName + " with number " + publicHealthNumber + " has been edited.");
            op.clear();
            viewHelper();

            changesSaved = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes a patient from list of patients
    private void commandDelete() {
        int publicHealthNumber = 0;

        viewHelper();

        try {
            publicHealthNumber = Integer.parseInt(op.getPublicHealthNumber());
        } catch (NumberFormatException ex) {
            //
        }

        boolean validInput = validateInput(publicHealthNumber);

        if (validInput) {
            if (!patientRecords.getRecords().containsKey(publicHealthNumber)) {
                op.feedback("Patient does not exist.");
                return;
            }

            String fullName = patientRecords.getRecords().get(publicHealthNumber).getFullName();

            patientRecords.deletePatient(publicHealthNumber);
            op.feedback(("\nPatient " + fullName + " with number " + publicHealthNumber + " has been deleted."));
            op.clear();
            viewHelper();

            changesSaved = false;
        }
    }




    private void commandView() {
        viewHelper();
        op.feedback("");
    }



    // EFFECTS: shows user all the patients in the patient records
    private void viewHelper() {
        String s = "";
        for (Patient p: patientRecords.getRecords().values()) {
            s += p.getPublicHealthNumber() + ": " + p.getFullName() + "\n";
        }
        op.view(s);
    }

    // EFFECTS: saves the patient records to file
    private void save() {
        if (!changesSaved) {
            try {
                jsonWriter.open();
                jsonWriter.write(patientRecords);
                jsonWriter.close();
                op.save();
                op.feedback("Saved patient records");
            } catch (FileNotFoundException e) {
                op.feedback("Unable to save files.");
            }

            changesSaved = true;
        } else {
            op.feedback("No changes to save.");
        }

    }



    // MODIFIES: this
    // EFFECTS: loads patient records from file
    private void load() {

        try {
            patientRecords = jsonReader.read();
            op.feedback("Loaded patient records.");
        } catch (IOException e) {
            op.feedback("Unable to load file.");
        }

    }


    // EFFECTS: if a user quits the applications after making changes, prompts the user
    // to save patient records, otherwise returns false

    private void quit() {

        if (!changesSaved) {


            promptSave();
        } else {
            System.exit(0);
        }
    }


    // EFFECTS: if user inputs "y", saves patient records to database
    // and quits the application, if user inputs "n", quits the application,
    // otherwise prompts user to try again
    private void promptSave() {
        op.saveQuit();
        pack();
    }


    // EFFECTS: checks whether the public health number entered is valid or not
    // if not valid, displays an invalid message
    private boolean validateInput(int publicHealthNumber) {
        boolean validInput = true;

        if (publicHealthNumber <= 0) {
            op.feedback("Invalid Public Health Number. Please try again.");
            op.clear();
            validInput = false;
        }

        return validInput;
    }



    // EFFECTS: checks whether the patient name entered is valid or not
    // if not valid, displays an invalid message
    private boolean validateInput(int publicHealthNumber, String fullName) {
        boolean validInput = true;

        validInput = validateInput(publicHealthNumber);

        if (fullName == null || fullName.isEmpty() || fullName.trim().isEmpty()) {
            op.feedback("Invalid Name. Please try again.");
            op.clear();
            validInput = false;
        }

        return validInput;
    }


    // EFFECTS: plays sound when buttons are clicked
    public void playSound() {

        soundPlayer.play();
    }


}
