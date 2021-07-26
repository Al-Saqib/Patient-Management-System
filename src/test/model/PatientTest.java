package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void runBefore() {
        patient1 = new Patient("John Smith", 1234);
        patient2= new Patient("Margot Robbie", 12345);
         }


    @Test
    void testGetFullName() {
        assertEquals("John Smith", patient1.getFullName());
        assertEquals("Margot Robbie", patient2.getFullName());


    }


    @Test
    void testGetPublicHealthNumber() {
        assertEquals(12345, patient2.getPublicHealthNumber());
        assertEquals(1234, patient1.getPublicHealthNumber());

    }






}