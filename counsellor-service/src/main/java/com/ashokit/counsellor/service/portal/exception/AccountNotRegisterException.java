package com.ashokit.counsellor.service.portal.exception;

public class AccountNotRegisterException extends RuntimeException{
    String message;
    public AccountNotRegisterException(){

    }
    public AccountNotRegisterException(String message){
        super(message);
    }
}
