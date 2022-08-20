package model;

public enum BedEnum {
    //A bed has three statues so that to change color of the bed in GUI
    FEMALE ("Female", "red color"),
    MALE ("Male", "blue color"),
    EMPTY ("Empty", "No color");

    private final String bedStatus;
    private final String bedColor;

    BedEnum(String bedStatus, String bedColor){
        this.bedStatus = bedStatus;
        this.bedColor = bedColor;
    }



}
