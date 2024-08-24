package com.ashokit.counsellor.service.portal.exception;

public class CounsellorDoesNotExistException extends RuntimeException{
    String message;
    public CounsellorDoesNotExistException(){

    }
    public CounsellorDoesNotExistException(String message){
        super(message);
    }
}
