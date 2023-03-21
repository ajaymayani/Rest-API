package com.example.restapi.exceptions;

public class CustomException extends RuntimeException{

    public CustomException(String msg)
    {
        super(msg);
    }
}
