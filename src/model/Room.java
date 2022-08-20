package model;

import java.util.HashMap;

public class Room {
    private int rNum;
    private HashMap<Integer, Bed> bedMap ;

    public Room(int rNum){
        this.rNum = rNum;
        this.bedMap = new HashMap<>();
        roomInitialize(rNum);
    }

    //this information should save in wards
    public void roomInitialize(int rNum) {
        if (rNum ==101 || rNum ==201){
            bedMap.put((rNum*100+1), new Bed((rNum*100+1),BedEnum.EMPTY) );
        }else if (rNum ==102 || rNum ==202){
            bedMap.put((rNum*100+1), new Bed((rNum*100+1),BedEnum.EMPTY) );
            bedMap.put((rNum*100+2), new Bed((rNum*100+2),BedEnum.EMPTY) );
        }else{
            bedMap.put((rNum*100+1), new Bed((rNum*100+1),BedEnum.EMPTY) );
            bedMap.put((rNum*100+2), new Bed((rNum*100+2),BedEnum.EMPTY) );
            bedMap.put((rNum*100+3), new Bed((rNum*100+3),BedEnum.EMPTY) );
            bedMap.put((rNum*100+4), new Bed((rNum*100+4),BedEnum.EMPTY) );
        }

    }

    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rNum) {
        this.rNum = rNum;
    }

    public HashMap<Integer, Bed> getBedMap() {
        return bedMap;
    }

    public void setBedMap(HashMap<Integer, Bed> bedMap) {
        this.bedMap = bedMap;
    }
}
