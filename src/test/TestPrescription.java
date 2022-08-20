package test;

import model.Medicine;
import model.Prescription;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestPrescription {


    @Test
    public void testMedicine(){
        Prescription p = new Prescription(123,123,"headache");
        Medicine m1 = new Medicine("paracetamol",3,2,"pcs");
        Medicine m2 = new Medicine(" vitaminC",3,1,"pcs");
        p.addMedicine("paracetamol",m1);
        p.addMedicine("vitaminC",m2);
        assertTrue(p.getMedcineMap().size() ==2);
    }

}
