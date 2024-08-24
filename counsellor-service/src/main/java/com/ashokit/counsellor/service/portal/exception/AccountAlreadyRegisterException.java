package com.ashokit.counsellor.service.portal.exception;

public class AccountAlreadyRegisterException extends RuntimeException{
    String message;
    public AccountAlreadyRegisterException(){

    }
    public AccountAlreadyRegisterException(String message){
        super(message);

    }
}
