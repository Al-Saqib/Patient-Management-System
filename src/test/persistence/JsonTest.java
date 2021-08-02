package persistence;

import model.Patient;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkPatient(String name, int publicHealthNumber, Patient patient) {
        assertEquals(name, patient.getFullName());
        assertEquals(publicHealthNumber, patient.getPublicHealthNumber());
    }
}
