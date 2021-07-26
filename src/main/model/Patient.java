package model;


// Represents a patient to be managed by the diagnostic center administration
// with patient's name, date of birth, gender and public health number.


public class Patient {
    private String fullName;
    private int publicHealthNumber;

    // EFFECTS: patient has a full name and a public health number.
    public Patient(String fullName, int publicHealthNumber) {
        this.fullName = fullName;
        this.publicHealthNumber = publicHealthNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPublicHealthNumber() {
        return publicHealthNumber;
    }











}
