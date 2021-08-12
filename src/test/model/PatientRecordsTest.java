package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for ListOfPatients class
public class PatientRecordsTest {

    private PatientRecords records;
    int number = 123;
    String name = "John Smith";

    @BeforeEach
    void runBefore() {
        records = new PatientRecords();


    }


    @Test
    void testAddPatient() {

        records.addPatient(number, name);
        Patient j = records.getPatientByID(number);
        assertEquals(number, j.getPublicHealthNumber());
        assertEquals(name, j.getFullName());

    }

    @Test
    void testDeletePatient() {

        records.addPatient(number, name);
        Patient j = records.getPatientByID(number);
        assertNotNull(j);
        records.deletePatient(number);
        j = records.getPatientByID(number);
        assertNull(j);

    }

    @Test
    void testEditPatient() {
        records.addPatient(number, name);
        String name1 = "Margot Robbie";
        int number1 = 231;
        records.editPatient(name1, number);

        Patient p = records.getPatientByID(number);

        assertEquals(name1, p.getFullName());

        records.editPatient(name1, number1);

        Patient j = records.getPatientByID(number1);
        assertNull(j);


    }

    @Test
    void testGetListOfPatients() {
        records.addPatient(number, name);
        Map<Integer, Patient> cap = records.getRecords();

        Patient j = records.getPatientByID(number);
        Patient p = cap.get(number);

        assertTrue(p.getFullName() == j.getFullName());
        assertTrue(p.getPublicHealthNumber() == j.getPublicHealthNumber());





    }
}
