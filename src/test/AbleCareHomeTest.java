package test;



import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AbleCareHomeTest {
    AbleCareHome a;
    Patient p ;

    @Before
    public void beforeTest(){
        a= new AbleCareHome();
        p = new Patient(0,"AAA","female","no", "null",1);
        a.getPatientList().add(p);
    }

    @Test
    public void testWardInCareHome(){
        ArrayList<Bed> beds = new ArrayList<>();
        for(Ward w: a.getWard() ){
            for(Room r: w.getRoomMap().values()){
                for(Bed b: r.getBedMap().values()){
                   beds.add(b);
                }
            }
        }
    assertEquals(beds.size(),38);

    }


    @Test
    public void testinitlizeCareHome()  {
        a.initlizeCareHome();
        assertEquals(a.getWard().length, 2);
    }

    @Test
    public void testGetPatient(){
        assertEquals(p,a.getPatient(0));
    }


}
