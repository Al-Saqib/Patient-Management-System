package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ListOfPatients;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfPatientsTest {

    private ListOfPatients map;
    int number = 123;
    String name = "John Smith";

    @BeforeEach
    void runBefore() {
        map = new ListOfPatients();


    }


    @Test
    void testAddPatient() {

        map.addPatient(number, name);
        Patient j = map.viewPatient(number);
        assertEquals(number, j.getPublicHealthNumber());
        assertEquals(name, j.getFullName());

    }

    @Test
    void testDeletePatient() {

        map.addPatient(number, name);
        Patient j = map.viewPatient(number);
        assertNotNull(j);
        map.deletePatient(number);
        j = map.viewPatient(number);
        assertNull(j);

    }

    @Test
    void testEditPatient() {
        map.addPatient(number, name);
        String name1 = "Margot Robbie";
        map.editPatient(name1, number);

        Patient p = map.viewPatient(number);

        assertEquals(name1, p.getFullName());




    }


}
