package model;


public class NoPatientException extends Exception{
    //the exception should have an UID
    static  final long serialVersionUID= 4L;

    //if someone click an no patient bed ,print the error message
    public NoPatientException(){
    }
}
