package model;

import model.Patient;
import java.util.HashMap;
import java.util.Scanner;

// Represents a list of patients to be handled by the diagnostic center
// management team
public class PatientRecords {

    private HashMap<Integer, Patient> patientMap;

    public PatientRecords() {
        patientMap = new HashMap<>();

    }


    // MODIFIES: this
    // EFFECTS: adds a patient to the list of patients
    public void addPatient(int publicHealthNumber, String fullName) {
        patientMap.put(publicHealthNumber, new Patient(fullName, publicHealthNumber));
    }


    // EFFECTS: returns a patient if a patient with the given public health
    // number exists in the list of patients
    public Patient getPatientByID(int publicHealthNumber) {
        Patient p = patientMap.get(publicHealthNumber);
        return p;
    }


    // MODIFIES: this
    // EFFECTS: if a patient with the given public health number exists, changes
    // the name of the patient to the given name. Otherwise, returns the patient.
    public Patient editPatient(String fullName, int publicHealthNumber) {
        Patient j = patientMap.get(publicHealthNumber);

        if (j != null) {
            j.setFullName(fullName);
        }

        return j;
    }


    // MODIFIES: this
    // EFFECTS: if a patient with the given public health
    // number exists, it deletes that patient from the list.
    public void deletePatient(int publicHealthNumber) {
        patientMap.remove(publicHealthNumber);
    }



    // EFFECTS: returns a Hashmap
    public HashMap<Integer, Patient> getListOfPatients() {
        return patientMap;
    }

}
