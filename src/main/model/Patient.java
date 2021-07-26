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


    // EFFECTS: returns full name
    public String getFullName() {
        return fullName;
    }


    // EFFECTS: returns public health number
    public int getPublicHealthNumber() {
        return publicHealthNumber;
    }


    // MODIFIES: this
    // EFFECTS: the existing full name is changed to a new full name
    public void setFullName(String fullName) {
        this.fullName = fullName;

    }

    // MODIFIES:
    // EFFECTS:
    @Override
    public boolean equals(Object obj) {
        Patient p = (Patient) obj;
        boolean equal = p.getFullName() == this.fullName && p.getPublicHealthNumber() == this.publicHealthNumber;
        return equal;
    }
}
