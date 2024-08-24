package com.ashokit.counsellor.service.portal.exception;

public class EmailOrPasswordMissMatchException extends RuntimeException{
    String message;
    public EmailOrPasswordMissMatchException(){

    }
    public EmailOrPasswordMissMatchException(String message){
        super(message);
    }
}
