package model;

import java.util.HashMap;
public class Ward {

    private String wName;
    private HashMap<Integer, Room> roomMap;


    public Ward(String wName) {
        this.wName = wName;
        this.roomMap = new HashMap<>();
        initilizeWard(wName);
    }

    //InitilizeWard depend on Able carehome request
    public void initilizeWard(String wName){
        if(wName.equals("w1")){
            roomMap.put(101,new Room(101));
            roomMap.put(102,new Room(102));
            roomMap.put(103,new Room(103));
            roomMap.put(104,new Room(104));
            roomMap.put(105,new Room(105));
            roomMap.put(106,new Room(106));
        }else if(wName.equals("w2")){
            roomMap.put(201,new Room(201));
            roomMap.put(202,new Room(202));
            roomMap.put(203,new Room(203));
            roomMap.put(204,new Room(204));
            roomMap.put(205,new Room(205));
            roomMap.put(206,new Room(206));
        }

    }

    public HashMap<Integer, Room> getRoomMap() {
        return roomMap;
    }

}
