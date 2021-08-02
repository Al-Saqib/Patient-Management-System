package persistence;


import model.Patient;
import model.PatientRecords;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PatientRecords pr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testEmpty.json");
        try {
            PatientRecords pr = reader.read();
            assertEquals(0, pr.getRecords().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testGeneralDB.json");
        try {
            PatientRecords pr = reader.read();
            Collection<Patient> patientsC = pr.getRecords().values();
            ArrayList<Patient> patients = new ArrayList<Patient>(patientsC);

            assertEquals(3, patients.size());
            checkPatient("Mofiz Uddin", 421, patients.get(0));
            checkPatient("Abbasuddin", 123, patients.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}