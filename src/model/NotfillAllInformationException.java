package model;


public class NotfillAllInformationException extends  Exception{
        // the exception should have an UID
        static  final long serialVersionUID =3L;

        //if some information not fill, print the errormessage
        public NotfillAllInformationException(String errMessage){
            System.err.println(errMessage+ "should be fill! ");

        }
    }

