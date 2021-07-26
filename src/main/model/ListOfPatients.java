package model;

// Represents a list of patients to be handled by the management team

import model.Patient;

import java.util.HashMap;
import java.util.Scanner;

public class ListOfPatients {

    private HashMap<Integer, Patient> patientMap;
//    private Scanner input;

    public ListOfPatients() {
        patientMap = new HashMap<>();
//        input = new Scanner(System.in);
//        boolean keepRunning = true;
//        String command = null;
//
//        while (keepRunning) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepRunning = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("\nGoodbye!");
//    }
//
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\ta -> add");
//        System.out.println("\te -> edit");
//        System.out.println("\tv -> view");
//        System.out.println("\td -> delete");
//        System.out.println("\tq -> quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("a")) {
//            commandAdd();
//        } else if (command.equals("e")) {
//            commandEdit();
//        } else if (command.equals("v")) {
//            commandView();
//        } else if (command.equals("d")) {
//            commandDelete();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    public void commandAdd() {
//        System.out.println("\nPlease enter public health number of patient:");
//        System.out.println("If you don't want to do this operation, enter a negative number.");
//
//        while (!input.hasNextInt()) {
//            System.out.println("That's not a number!");
//            input.next(); // this is important!
//        }
//
//        int publicHealthNumber = input.nextInt();
//
//        if (publicHealthNumber < 0) {
//            return;
//        }
//
//        System.out.println("\nPlease enter full name of patient:");
//        String fullName = input.next();
//        addPatient(publicHealthNumber, fullName);
//    }
//
//    public void commandView() {
//        for (Patient p: patientMap.values()) {
//            System.out.println(p.getPublicHealthNumber() + ": " + p.getFullName());
//        }
//    }
//
//    public void commandEdit() {
//        commandView();
//        System.out.println("\nPlease enter public health number of patient:");
//        System.out.println("If you don't want to do this operation, enter a negative number.");
//
//        while (!input.hasNextInt()) {
//            System.out.println("That's not a number!");
//            input.next(); // this is important!
//        }
//
//        int publicHealthNumber = input.nextInt();
//
//        if (publicHealthNumber < 0) {
//            return;
//        }
//
//        System.out.println("\nPlease enter full name of patient:");
//        String fullName = input.next();
//        editPatient(fullName, publicHealthNumber);
//
//
//
//    }
//
//
//
//    public void commandDelete() {
//
//        commandView();
//        System.out.println("\nPlease enter public health number of patient:");
//        System.out.println("If you don't want to do this operation, enter a negative number.");
//
//        while (!input.hasNextInt()) {
//            System.out.println("That's not a number!");
//            input.next(); // this is important!
//        }
//
//        int publicHealthNumber = input.nextInt();
//
//        if (publicHealthNumber < 0) {
//            return;
//        }
//
////        System.out.println("\nPlease enter full name of patient:");
////        String fullName = input.next();
//        deletePatient(publicHealthNumber);
//
    }

    public void addPatient(int publicHealthNumber, String fullName) {
        patientMap.put(publicHealthNumber, new Patient(fullName, publicHealthNumber));
    }

    public Patient getPatientByID(int publicHealthNumber) {
        Patient p = patientMap.get(publicHealthNumber);
        return p;
    }

    public Patient editPatient(String fullName, int publicHealthNumber) {
        Patient j = patientMap.get(publicHealthNumber);

        if (j != null) {
            j.setFullName(fullName);
        }

        return j;
    }

    public void deletePatient(int publicHealthNumber) {
        patientMap.remove(publicHealthNumber);






    }

    public HashMap<Integer, Patient> getListOfPatients() {
        return patientMap;
    }

}
