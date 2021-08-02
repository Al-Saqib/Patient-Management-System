package persistence;


import model.Patient;
import model.PatientRecords;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            PatientRecords pr = new PatientRecords();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            PatientRecords pr = new PatientRecords();
            JsonWriter writer = new JsonWriter("./data/testEmpty.json");
            writer.open();
            writer.write(pr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmpty.json");
            pr = reader.read();
            assertEquals(0, pr.getRecords().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PatientRecords pr = new PatientRecords();
            pr.addPatient(123, "Abbasuddin");
            pr.addPatient(918, "John Smith");
            pr.addPatient(421, "Mofiz Uddin");
            JsonWriter writer = new JsonWriter("./data/testGeneralDB.json");
            writer.open();
            writer.write(pr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneralDB.json");
            pr = reader.read();

            Collection<Patient> patientsC = pr.getRecords().values();
            ArrayList<Patient> patients = new ArrayList<Patient>(patientsC);
            assertEquals(3, patients.size());
            checkPatient("Mofiz Uddin", 421, patients.get(0));
            checkPatient("Abbasuddin", 123, patients.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}