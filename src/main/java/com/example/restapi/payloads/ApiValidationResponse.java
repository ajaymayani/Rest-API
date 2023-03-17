package com.example.restapi.payloads;

import lombok.Data;

import java.util.Map;

@Data
public class ApiValidationResponse {
    private boolean success;
    private Map<String,String> errors;
}
