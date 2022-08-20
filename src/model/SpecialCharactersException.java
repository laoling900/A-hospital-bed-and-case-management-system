package model;

public class SpecialCharactersException extends Exception{
    //the exception should have an UID
    static final long serialVersionUID =2L;

    // if SpecialCharactersException happened, print the errormessage
    public SpecialCharactersException(String errMessage){
        System.err.println(errMessage+"not allow special characters such as %@#");
    }
}

