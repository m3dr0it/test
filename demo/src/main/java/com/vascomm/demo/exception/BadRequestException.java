package com.vascomm.demo.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String msg){
        super(msg);
    }
}
