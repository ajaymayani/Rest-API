package com.example.restapi.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;
    private String username;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String username) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,username));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.username = username;
    }
}
