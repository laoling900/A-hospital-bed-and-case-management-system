package test;


import model.Bed;
import model.Room;
import org.junit.Test;


import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class testRoom {

    @Test
     public void testRoomInitialize1(){
        Room r1 = new Room(101);
        HashMap<Integer, Bed> bedMap = r1.getBedMap();
        assertEquals(10101, bedMap.get(10101).getbID());
        assertEquals(1, r1.getBedMap().size());
    }

    @Test
    public void testRoomInitialize2(){
        Room r2 = new Room(102);
        HashMap<Integer, Bed> bedMap = r2.getBedMap();
        assertEquals(10201, bedMap.get(10201).getbID());
        assertEquals(10202, bedMap.get(10202).getbID());
        assertEquals(2, r2.getBedMap().size());
    }

    @Test
    public void testRoomInitialize3(){
        Room r2 = new Room(103);
        HashMap<Integer, Bed> bedMap = r2.getBedMap();
        assertEquals(10301, bedMap.get(10301).getbID());
        assertEquals(10302, bedMap.get(10302).getbID());
        assertEquals(10303, bedMap.get(10303).getbID());
        assertEquals(10304, bedMap.get(10304).getbID());
        assertEquals(4, r2.getBedMap().size());
    }

}
