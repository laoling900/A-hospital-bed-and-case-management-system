package test;

import model.Room;
import model.Ward;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testWard {
    Ward w1;
    Ward w2;
    HashMap<Integer, Room> roomMap1;
    HashMap<Integer, Room> roomMap2;

    @Before
    public  void beforeTest(){
        w1 = new Ward("w1");
        w2 = new Ward("w2");
        roomMap1 =w1.getRoomMap();
        roomMap2 =w2.getRoomMap();
    }


    @Test   //test ward1 roomNo no issues, has 6 rooms
    public void testWard1(){
        assertTrue(roomMap1.get(101).getrNum() ==101);
        assertEquals(roomMap1.get(102).getrNum(),102);
        assertEquals(roomMap1.size(),6);
    }

    @Test    //test ward2 roomNo no issues, has 6 rooms
    public void testWard2(){
        assertTrue(roomMap2.get(201).getrNum() ==201);
        assertEquals(roomMap2.get(202).getrNum(),202);
        assertEquals(roomMap2.size(),6);
    }

    @Test
    public  void testWard1Rooms(){  //test any rooms in ward1 has beds
        assertTrue(roomMap1.get(101).getBedMap().size() ==1);
        assertTrue(roomMap1.get(102).getBedMap().size() ==2);
        assertTrue(roomMap1.get(103).getBedMap().size() ==4);
    }
    @Test  //test any rooms in ward2 has beds
    public  void testWard2Rooms(){
        assertTrue(roomMap2.get(201).getBedMap().size() ==1);
        assertTrue(roomMap2.get(202).getBedMap().size() ==2);
        assertTrue(roomMap2.get(203).getBedMap().size() ==4);
    }

}
