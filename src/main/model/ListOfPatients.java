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
