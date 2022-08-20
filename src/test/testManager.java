package test;


import model.*;

import model.Patient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class testManager {
    AbleCareHome a ;
    Manager m;
    @Before
    public void beforeTest(){
        a = new AbleCareHome();
        m = new Manager();
    }

    @Test
    public void testAddPatient(){
        Patient p = m.addPatient(a,"Kate","FEMALE","yes");

        assertTrue(p.getName().equals("Kate"));
    }

    @Test
    public void testAddStaff(){
        Staff s = new Staff(0,"Lujiang", "niubi","NURSE");
        m.addStaff(a,s);
        assertEquals("Lujiang", a.getStaffList().get(a.getStaffList().size()-1).getName());
    }

}
