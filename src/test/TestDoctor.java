package test;

import model.Doctor;
import model.Medicine;
import model.Prescription;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestDoctor {
    Doctor d ;
    Prescription pre ;
    Medicine m1;
    Medicine m2;

    @Before
    public void beforeTest(){
        d = new Doctor();
        pre = new Prescription(1,2,"Fever");
        m1 = new Medicine("m1", 3,3,"pcs");
        m2 = new Medicine("m2", 5,4,"bottle");
        pre.getMedcineMap().put("m1", m1);
        pre.getMedcineMap().put("m2",m2);
        d.getPrescriptionMap().put(111, pre);
    }


    @Test
    public void testAllowAddPre(){
        assertEquals(false, d.allowAddPre(0,"yes"));
        assertEquals(false, d.allowAddPre(1, "yes"));
        assertEquals(true,d.allowAddPre(11,"null"));
    }

    @Test
    public void testCreateMedicine(){
        Medicine m3 = d.createMedicine("m3", 7,8,"pcs");
        assertEquals(7, m3.getTime());
        assertEquals(8, m3.getUnit());
    }


    @Test
    public void testCheckInt(){
        assertEquals(false, d.checkInt(")(&&#@^"));
    }
}
