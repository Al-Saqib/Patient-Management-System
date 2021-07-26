package ui;

import model.PatientRecords;
import model.Patient;
import java.util.Scanner;

// console implementation is inspired by the teller
// app example provided for project phase 1

// Patient Management Application
public class PatientDatabase {

    private PatientRecords lisp;
    private Scanner input;

    // EFFECTS: runs the patient management application
    public PatientDatabase() {
        lisp = new PatientRecords();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void systemApp() {
        input = new Scanner(System.in).useDelimiter("\\n");;
        boolean keepRunning = true;
        String command = null;

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
        } else {
            System.out.println("Selection not recognized...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a patient to the list of patients
    private void commandAdd() {
        System.out.println("\nPlease enter public health number of patient:");
        System.out.println("If you don't want to do this operation, enter a negative number.");

        while (!input.hasNextInt()) {
            System.out.println("That's not a number!");
            input.next(); // this is important!
        }

        int publicHealthNumber = input.nextInt();

        if (publicHealthNumber < 0) {
            return;
        }

        System.out.println("\nPlease enter full name of patient:");
        String fullName = input.next();
        lisp.addPatient(publicHealthNumber, fullName);
    }


    // EFFECTS: shows the patients in the list of patients
    private void commandView() {
        for (Patient p: lisp.getListOfPatients().values()) {
            System.out.println(p.getPublicHealthNumber() + ": " + p.getFullName());
        }
    }

    // MODIFIES: this
    // EFFECTS: edits full name of the patient
    private void commandEdit() {
        commandView();
        System.out.println("\nPlease enter public health number of patient:");
        System.out.println("If you don't want to do this operation, enter a negative number.");

        while (!input.hasNextInt()) {
            System.out.println("That's not a number!");
            input.next();
        }

        int publicHealthNumber = input.nextInt();

        if (publicHealthNumber < 0) {
            return;
        }

        System.out.println("\nPlease enter full name of patient:");
        String fullName = input.next();
        lisp.editPatient(fullName, publicHealthNumber);



    }


    // MODIFIES: this
    // EFFECTS: deletes a patient from list of patients
    private void commandDelete() {

        commandView();
        System.out.println("\nPlease enter public health number of patient:");
        System.out.println("If you don't want to do this operation, enter a negative number.");

        while (!input.hasNextInt()) {
            System.out.println("That's not a number!");
            input.next(); // this is important!
        }

        int publicHealthNumber = input.nextInt();

        if (publicHealthNumber < 0) {
            return;
        }

//        System.out.println("\nPlease enter full name of patient:");
//        String fullName = input.next();
        lisp.deletePatient(publicHealthNumber);

    }

}
