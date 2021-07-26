package ui;

import model.ListOfPatients;
import model.Patient;

import java.util.Scanner;

public class PatientRecordSystem {

    private ListOfPatients lisp;
    private Scanner input;


    public PatientRecordSystem() {
        lisp = new ListOfPatients();
    }

    public void systemApp() {
        input = new Scanner(System.in);
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

        System.out.println("\nGoodbye!");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
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
            System.out.println("Selection not valid...");
        }
    }

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

    private void commandView() {
        for (Patient p: lisp.getListOfPatients().values()) {
            System.out.println(p.getPublicHealthNumber() + ": " + p.getFullName());
        }
    }

    private void commandEdit() {
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

        System.out.println("\nPlease enter full name of patient:");
        String fullName = input.next();
        lisp.editPatient(fullName, publicHealthNumber);



    }



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
