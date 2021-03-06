package ui;


import model.PatientRecords;
import model.Patient;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


// console implementation is inspired by the teller
// app example provided for project phase 1

// data persistence implementation is inspired by JSon
// Serialization Demo provided for project phase 2


// Represents the patient management application

public class PatientDatabase {
    private static final String JSON_STORE = "./data/database.json";

    private JsonWriter jsonWriter;
    private boolean changesSaved;
    private JsonReader jsonReader;
    private PatientRecords patientRecords;
    private Scanner input;


    // EFFECTS: constructs the patient management application
    public PatientDatabase() {
        patientRecords = new PatientRecords();
    }


    // MODIFIES: this
    // EFFECTS: processes user input

    public void systemApp() {
        input = new Scanner(System.in).useDelimiter("\\n");
        boolean keepRunning = true;
        changesSaved = true;
        String command = null;
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        load();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = quit();
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you later!");
    }


    // EFFECTS: displays choices of operations to user

    private void displayMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\ta -> add");
        System.out.println("\te -> edit");
        System.out.println("\tv -> view");
        System.out.println("\td -> delete");
        System.out.println("\ts -> save");
        System.out.println("\tl -> load");
        System.out.println("\tq -> quit");

    }


    // MODIFIES: this
    // EFFECTS: processes user commands

    private void processCommand(String command) {
        if (command.equals("a")) {
            commandAdd();
        } else if (command.equals("e")) {
            commandEdit();
        } else if (command.equals("v")) {
            commandView();
        } else if (command.equals("d")) {
            commandDelete();
        } else if (command.equals("s")) {
            save();
        } else if (command.equals("l")) {
            load();
        } else {
            System.out.println("Selection not recognized...");
        }
    }


    // MODIFIES: this
    // EFFECTS: prompts user for name and public health number of patient and
    // adds to patient records

    private void commandAdd() {
        int publicHealthNumber = processInt();

        if (publicHealthNumber < 0) {
            return;
        }

        if (patientRecords.getRecords().containsKey(publicHealthNumber)) {
            System.out.println("Patient already exists. Use edit function to change personal information.");
            return;
        }

        System.out.println("\nPlease enter full name of patient:");
        String fullName = input.next();

        patientRecords.addPatient(publicHealthNumber, fullName);
        System.out.println("\nPatient " + fullName + " with number " + publicHealthNumber + " has been added.");

        changesSaved = false;
    }


    // EFFECTS: shows user all the patients in the patient records

    private void commandView() {
        for (Patient p: patientRecords.getRecords().values()) {
            System.out.println(p.getPublicHealthNumber() + ": " + p.getFullName());
        }
    }


    // MODIFIES: this
    // EFFECTS: edits full name of the patient in the patient records

    private void commandEdit() {
        commandView();
        int publicHealthNumber = processInt();

        if (publicHealthNumber < 0) {
            return;
        }

        if (!patientRecords.getRecords().containsKey(publicHealthNumber)) {
            System.out.println("Patient doesn't exist. Use add function to add patient.");
            return;
        }

        System.out.println("\nPlease enter full name of patient:");
        String fullName = input.next();
        patientRecords.editPatient(fullName, publicHealthNumber);

        System.out.println("\nPatient " + fullName + " with number " + publicHealthNumber + " has been edited.");

        changesSaved = false;
    }


    // MODIFIES: this
    // EFFECTS: deletes a patient from list of patients

    private void commandDelete() {
        commandView();
        int publicHealthNumber = processInt();

        if (publicHealthNumber < 0) {
            return;
        }

        if (!patientRecords.getRecords().containsKey(publicHealthNumber)) {
            System.out.println("Patient does not exist.");
            return;
        }

        String fullName = patientRecords.getRecords().get(publicHealthNumber).getFullName();

        patientRecords.deletePatient(publicHealthNumber);
        System.out.println("\nPatient " + fullName + " with number " + publicHealthNumber + " has been deleted.");

        changesSaved = false;
    }


    // EFFECTS: saves the patient records to file

    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(patientRecords);
            jsonWriter.close();
            System.out.println("Saved patient records to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

        changesSaved = true;



    }


    // MODIFIES: this
    // EFFECTS: loads patient records from file

    private void load() {

        try {
            patientRecords = jsonReader.read();
            System.out.println("Loaded patient records from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }


    // EFFECTS: if a user quits the applications after making changes, prompts the user
    // to save patient records, otherwise returns false

    private boolean quit() {

        if (!changesSaved) {


            promptSave();
        }

        return false;
    }


    // EFFECTS: if user inputs "y", saves patient records to database
    // and quits the application, if user inputs "n", quits the application,
    // otherwise prompts user to try again

    private void promptSave() {
        System.out.println("\nWould you prefer to save this database?:");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");

        String command = input.next();
        command = command.toLowerCase();

        while (!command.equals("y") || !command.equals("n")) {
            if (command.equals("y")) {
                save();
                return;
            } else if (command.equals("n")) {
                return;
            }
            System.out.println("Invalid input. Please try again.");
            command = input.next();

        }
    }


    // EFFECT: takes the user input and returns an integer if user input is integer;
    // otherwise prompts user for a valid input

    public int processInt() {
        System.out.println("\nPlease enter public health number of patient:");
        System.out.println("If you don't want to do this operation, enter a negative number.");

        while (!input.hasNextInt()) {
            System.out.println("That's not a number!");
            input.next(); // this is important!
        }

        int publicHealthNumber = input.nextInt();

        return publicHealthNumber;
    }

}
