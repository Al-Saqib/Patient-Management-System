package ui;

import model.PatientRecords;
import model.Patient;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// console implementation is inspired by the teller
// app example provided for project phase 1

// Patient Management Application
public class PatientDatabase {
    private static final String JSON_STORE = "./data/database.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private PatientRecords patientRecords;
    private Scanner input;

    // EFFECTS: runs the patient management application
    public PatientDatabase() {
        patientRecords = new PatientRecords();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void systemApp() {
        input = new Scanner(System.in).useDelimiter("\\n");;
        boolean keepRunning = true;
        String command = null;
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you later!");
    }

    // EFFECTS: display menu of choices to user
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
    // EFFECTS: processes user command
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
            commandSave();
        } else if (command.equals("l")) {
            commandLoad();
        } else {
            System.out.println("Selection not recognized...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a patient to the list of patients
    private void commandAdd() {
        int publicHealthNumber = processInt();

        if (publicHealthNumber < 0) {
            return;
        }

        if (patientRecords.getListOfPatients().containsKey(publicHealthNumber)) {
            System.out.println("Patient already exists. Use edit function to change personal information.");
            return;
        }

        System.out.println("\nPlease enter full name of patient:");
        String fullName = input.next();

        patientRecords.addPatient(publicHealthNumber, fullName);
        System.out.println("\nPatient " + fullName + " with number " + publicHealthNumber + " has been added.");
    }


    // EFFECTS: shows the patients in the list of patients
    private void commandView() {
        for (Patient p: patientRecords.getListOfPatients().values()) {
            System.out.println(p.getPublicHealthNumber() + ": " + p.getFullName());
        }
    }

    // MODIFIES: this
    // EFFECTS: edits full name of the patient
    private void commandEdit() {
        commandView();
        int publicHealthNumber = processInt();

        if (publicHealthNumber < 0) {
            return;
        }

        if (!patientRecords.getListOfPatients().containsKey(publicHealthNumber)) {
            System.out.println("Patient doesn't exist. Use add function to add patient.");
            return;
        }

        System.out.println("\nPlease enter full name of patient:");
        String fullName = input.next();
        patientRecords.editPatient(fullName, publicHealthNumber);

        System.out.println("\nPatient " + fullName + " with number " + publicHealthNumber + " has been edited.");
    }


    // MODIFIES: this
    // EFFECTS: deletes a patient from list of patients
    private void commandDelete() {
        commandView();
        int publicHealthNumber = processInt();

        if (publicHealthNumber < 0) {
            return;
        }

        if (!patientRecords.getListOfPatients().containsKey(publicHealthNumber)) {
            System.out.println("Patient does not exist.");
            return;
        }

        String fullName = patientRecords.getListOfPatients().get(publicHealthNumber).getFullName();

        patientRecords.deletePatient(publicHealthNumber);
        System.out.println("\nPatient " + fullName + " with number " + publicHealthNumber + " has been deleted.");
    }

    private void commandSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(patientRecords);
            jsonWriter.close();
            System.out.println("Saved patient records to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    private void commandLoad() {

        try {
            patientRecords = jsonReader.read();
            System.out.println("Loaded patient records from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    // EFFECT: takes the user input and returns if an integer, otherwise prompts user for a valid input
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
